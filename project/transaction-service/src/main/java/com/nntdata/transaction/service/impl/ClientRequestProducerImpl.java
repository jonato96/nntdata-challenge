package com.nntdata.transaction.service.impl;

import com.nntdata.transaction.config.RabbitMQConfig;
import com.nntdata.transaction.dto.client.ClientResponseDto;
import com.nntdata.transaction.service.ClientRequestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientRequestProducerImpl implements ClientRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public ClientResponseDto findClient(Long clientId) {
        ParameterizedTypeReference<ClientResponseDto> typeReference = new ParameterizedTypeReference<>(){};
        return rabbitTemplate.convertSendAndReceiveAsType(
                RabbitMQConfig.QUEUE_CLIENT,
                clientId,
                typeReference
        );
    }

}
