package com.nntdata.client.mapper;

import com.nntdata.client.dto.ClientDto;
import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper {
    public ClientResponseDto toClientDto(Client client){
        return new ClientResponseDto(
                client.getId(),
                client.getName(),
                client.getGender(),
                client.getAge(),
                client.getIdentification(),
                client.getAddress(),
                client.getPhone(),
                client.getStatus()
        );
    }

    public List<ClientResponseDto> toClientDtoList(List<Client> clients) {
        return clients.stream().map(this::toClientDto).toList();
    }

    public Client toClient(ClientDto clientDto){
        return new Client(
            clientDto.getId(),
            clientDto.getName(),
            clientDto.getGender(),
            clientDto.getAge(),
            clientDto.getIdentification(),
            clientDto.getAddress(),
            clientDto.getPassword(),
            clientDto.getPhone(),
            null
        );

    }

}
