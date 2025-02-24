package com.nntdata.client.service.impl;

import com.nntdata.client.config.RabbitMQConfig;
import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;
import com.nntdata.common.exception.GeneralException;
import com.nntdata.client.mapper.ClientMapper;
import com.nntdata.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientResponseCustomer {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT)
    public ClientResponseDto findClient(Long clientId) {
        Client clientFind = clientRepository.findById(clientId)
                .orElseThrow( () -> new GeneralException("Client not found with id: " + clientId));
        return clientMapper.toClientDto(clientFind);
    }

}
