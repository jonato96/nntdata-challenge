package com.nntdata.client.service.impl;

import com.nntdata.client.dto.ClientDto;
import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;
import com.nntdata.client.exception.GeneralException;
import com.nntdata.client.mapper.ClientMapper;
import com.nntdata.client.repository.ClientRepository;
import com.nntdata.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponseDto save(ClientDto requestClient) throws GeneralException {
        try {
            Client clientToCreate = clientMapper.toClient(requestClient);
            clientToCreate.setStatus(true);
            Client clientCreated = clientRepository.save(clientToCreate);
            return clientMapper.toClientDto(clientCreated);
        } catch (Exception ex){
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ClientResponseDto edit(ClientDto requestClient) throws GeneralException {
        try {
            Client clientFind = clientRepository.findByIdAndStatusTrue(requestClient.getId())
                    .orElseThrow( () -> new GeneralException("Client not found with id: " + requestClient.getId() + ", or is already inactive"));
            Client clientToEdit = clientMapper.toClient(requestClient);
            clientToEdit.setStatus(clientFind.isStatus());
            Client clientCreated = clientRepository.save(clientToEdit);
            return clientMapper.toClientDto(clientCreated);
        } catch (Exception ex){
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws GeneralException {
        validateExistsAndIsActive(id);
        clientRepository.inactivateClient(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDto findById(Long id) throws GeneralException {
        Client clientFind = clientRepository.findById(id)
                .orElseThrow( () -> new GeneralException("Client not found with id: " + id));
        return clientMapper.toClientDto(clientFind);
    }

    @Override
    public List<ClientResponseDto> findAll() throws GeneralException {
        List<Client> clients =  clientRepository.findAll();
        if (clients.isEmpty()) throw new GeneralException("Clients not found");
        return clientMapper.toClientDtoList(clients);
    }

    @Override
    @Transactional(readOnly = true)
    public Client validateClient(Long id) throws GeneralException {
        validateExistsAndIsActive(id);
        return clientRepository.findById(id).orElse(null);
    }

    private void validateExistsAndIsActive(Long id) throws GeneralException {
        boolean result = clientRepository.existsByIdAndStatusTrue(id);
        if (!result) throw new GeneralException("Client not found with id: " + id + ", or is already inactive");
    }
}
