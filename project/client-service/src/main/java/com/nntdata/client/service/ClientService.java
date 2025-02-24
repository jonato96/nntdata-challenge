package com.nntdata.client.service;

import com.nntdata.client.dto.ClientDto;
import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;
import com.nntdata.common.exception.GeneralException;

import java.util.List;

public interface ClientService {

    ClientResponseDto save(ClientDto requestClient) throws GeneralException;
    ClientResponseDto edit(ClientDto requestClient) throws GeneralException;
    void delete(Long id) throws GeneralException;
    ClientResponseDto findById(Long id) throws GeneralException;
    List<ClientResponseDto> findAll() throws GeneralException;
    Client validateClient(Long id) throws GeneralException;

}
