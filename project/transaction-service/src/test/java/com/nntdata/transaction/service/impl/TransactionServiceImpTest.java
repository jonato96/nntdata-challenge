package com.nntdata.transaction.service.impl;

import com.nntdata.common.exception.GeneralException;
import com.nntdata.transaction.dto.TransactionResponseDto;
import com.nntdata.transaction.entity.Transaction;
import com.nntdata.transaction.mapper.TransactionMapper;
import com.nntdata.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImpTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionMapper transactionMapper;
    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Test
    void testFindById() {
        // Mock Data
        Long id = 1L;

        Transaction transactionFind = new Transaction();
        transactionFind.setId(1L);
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transactionFind));

        TransactionResponseDto mapped = new TransactionResponseDto();
        mapped.setId(1L);
        when(transactionMapper.toResponseDto(transactionFind)).thenReturn(mapped);

        // Act
        TransactionResponseDto result = transactionServiceImpl.findById(id);
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_GeneralException() {
        // Mock Data
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());
        // Assert
        assertThrows(GeneralException.class, () -> transactionServiceImpl.findById(id));
    }

}
