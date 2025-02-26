package com.nntdata.transaction.service;

import com.nntdata.transaction.dto.TransactionDto;
import com.nntdata.transaction.dto.TransactionResponseDto;
import com.nntdata.transaction.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponseDto save(TransactionDto requestMovement);
    void delete(Long id);
    TransactionResponseDto findById(Long id);
    List<TransactionResponseDto> findByAccount(String account);
    List<Transaction> findByAccountAndDates(Long accountId, LocalDate start, LocalDate end);

}
