package com.nntdata.transaction.service.impl;

import com.nntdata.common.exception.GeneralException;
import com.nntdata.transaction.dto.AccountResponseDto;
import com.nntdata.transaction.dto.client.ClientResponseDto;
import com.nntdata.transaction.entity.Account;
import com.nntdata.transaction.mapper.AccountMapper;
import com.nntdata.transaction.repository.AccountRepository;
import com.nntdata.transaction.service.ClientRequestProducer;
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
public class AccountServiceImplTest {

    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ClientRequestProducer clientRequestProducer;
    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    void testFindAll() {
        // Mock Data
        Long id = 1L;

        Account accountFind = new Account();
        accountFind.setId(1L);
        accountFind.setClientId(2L);
        when(accountRepository.findById(id)).thenReturn(Optional.of(accountFind));

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setName("Jhon Doe");
        when(clientRequestProducer.findClient(accountFind.getClientId())).thenReturn(clientResponseDto);

        AccountResponseDto mapped = new AccountResponseDto();
        mapped.setId(accountFind.getId());
        when(accountMapper.toResponseDto(accountFind)).thenReturn(mapped);
        // Act
        AccountResponseDto result = accountServiceImpl.findById(id);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());

    }

    @Test
    void testFindById_GeneralException() {
        // Mock Data
        Long id = 1L;
        when(accountRepository.findById(id)).thenReturn(Optional.empty());
        // Assert
        assertThrows(GeneralException.class, () -> accountServiceImpl.findById(id));
    }

}
