package com.nntdata.transaction.service.impl;

import com.nntdata.transaction.dto.TransactionDto;
import com.nntdata.transaction.dto.TransactionResponseDto;
import com.nntdata.transaction.entity.Account;
import com.nntdata.transaction.entity.Transaction;
import com.nntdata.common.exception.GeneralException;
import com.nntdata.transaction.helper.TransactionType;
import com.nntdata.transaction.mapper.TransactionMapper;
import com.nntdata.transaction.repository.TransactionRepository;
import com.nntdata.transaction.service.AccountService;
import com.nntdata.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public TransactionResponseDto save (TransactionDto transactionRequest) {
        Account accountForTx =  accountService.validateAccount(transactionRequest.getAccountId());
        validateValue(transactionRequest, accountForTx.getBalance());

        BigDecimal actualBalance = BigDecimal.ZERO;
        if ( transactionRequest.getType() == TransactionType.CREDIT ) {
            actualBalance = accountForTx.getBalance().add(transactionRequest.getAmount());
        } else {
            actualBalance = accountForTx.getBalance().subtract(transactionRequest.getAmount());
        }

        Transaction transaction = transactionMapper.toTransaction(transactionRequest);
        transaction.setStatus(true);
        transaction.setBeforeBalance(accountForTx.getBalance());
        transaction.setAfterBalance(actualBalance);
        transaction.setDate(LocalDate.now());

        accountService.updateBalance(actualBalance, accountForTx.getId());
        return transactionMapper.toResponseDto(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validateExistsAndIsActive(id);
        Transaction txToDeactivate = transactionRepository.findById(id).orElse(new Transaction());
        Account accountToUpdate = txToDeactivate.getAccount();
        BigDecimal restoreBalance = accountToUpdate.getBalance().subtract(txToDeactivate.getAmount());
        accountService.updateBalance(restoreBalance,accountToUpdate.getId());
        transactionRepository.inactivateTransaction(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponseDto findById(Long id) {
        Optional<Transaction> movementFind = transactionRepository.findById(id);
        if (movementFind.isEmpty()) throw new GeneralException("Movement not found with id: " + id);
        return transactionMapper.toResponseDto(movementFind.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponseDto> findByAccount(String account) {
        List<Transaction> transactionList = transactionRepository.findAllByAccount(account);
        if ( transactionList.isEmpty() ) throw new GeneralException("Transactions with account number: " + account + " not found");
        return transactionMapper.toListResponseDto(transactionList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findByAccountAndDates(Long accountId, LocalDate start, LocalDate end) {
        return transactionRepository.findByAccountIdAndDateBetween(accountId, start, end);
    }

    private void validateExistsAndIsActive(Long id) {
        boolean result = transactionRepository.existsByIdAndStatusTrue(id);
        if (!result) throw new GeneralException("Transaction not found with id: " + id + ", or is already inactive");
    }

    private void validateValue(TransactionDto dto, BigDecimal actualBalance) {
        String msg = "";

        if ( dto.getAmount().compareTo(BigDecimal.ZERO) < 0 )
            msg = "Transactions only support unsigned numbers.";

        if ( (dto.getType().equals(TransactionType.DEBIT) || dto.getType().equals(TransactionType.CREDIT))
                && dto.getAmount().compareTo(BigDecimal.ZERO) == 0 )
            msg = "Transaction with zero amount is not valid.";

        if ( dto.getType().equals(TransactionType.DEBIT) && actualBalance.compareTo(dto.getAmount()) < 0 )
            msg = "Account Balance is not sufficient.";

        if ( !msg.isEmpty() ) throw new GeneralException(msg);
    }
}
