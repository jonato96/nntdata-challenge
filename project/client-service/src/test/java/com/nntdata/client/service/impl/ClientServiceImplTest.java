package com.nntdata.client.service.impl;

import com.nntdata.client.dto.ClientResponseDto;
import com.nntdata.client.entity.Client;
import com.nntdata.client.mapper.ClientMapper;
import com.nntdata.client.repository.ClientRepository;
import com.nntdata.common.exception.GeneralException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Test
    void testFindById() {
        // Mock Data
        Long id = 1L;

        Client clientFind = new Client();
        clientFind.setId(id);
        when(clientRepository.findById(id)).thenReturn(Optional.of(clientFind));

        ClientResponseDto mapped = new ClientResponseDto();
        mapped.setId(clientFind.getId());
        when(clientMapper.toClientDto(clientFind)).thenReturn(mapped);
        // Act
        ClientResponseDto result = clientServiceImpl.findById(id);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_GeneralException() {
        // Mock Data
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());
        // Assert
        assertThrows(GeneralException.class, () -> clientServiceImpl.findById(id));
    }

}
