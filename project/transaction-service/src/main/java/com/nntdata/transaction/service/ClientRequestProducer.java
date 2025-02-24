package com.nntdata.transaction.service;

import com.nntdata.transaction.dto.client.ClientResponseDto;

public interface ClientRequestProducer {
    ClientResponseDto findClient(Long clientId);
}
