package com.nntdata.transaction.service;

import com.nntdata.transaction.dto.TransactionDto;
import com.nntdata.transaction.dto.TransactionResponseDto;
import com.nntdata.transaction.entity.Transaction;
import com.nntdata.common.exception.GeneralException;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponseDto save(TransactionDto requestMovement) throws GeneralException;
    void delete(Long id) throws GeneralException;
    TransactionResponseDto findById(Long id) throws GeneralException;
    List<TransactionResponseDto> findByAccount(String account) throws GeneralException;
    List<Transaction> findByAccountAndDates(Long accountId, LocalDate start, LocalDate end);

}
