package com.nntdata.client.service;

import com.nntdata.client.dto.ClientDto;
import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;

import java.util.List;

public interface ClientService {

    ClientResponseDto save(ClientDto requestClient);
    ClientResponseDto edit(ClientDto requestClient);
    void delete(Long id);
    ClientResponseDto findById(Long id);
    List<ClientResponseDto> findAll();
    Client validateClient(Long id);

}
