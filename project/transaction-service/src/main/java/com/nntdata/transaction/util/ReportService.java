package com.nntdata.transaction.util;

import com.nntdata.transaction.dto.ReportResponseDto;
import com.nntdata.transaction.dto.client.ClientResponseDto;
import com.nntdata.transaction.entity.Account;
import com.nntdata.transaction.entity.Transaction;
import com.nntdata.common.exception.GeneralException;
import com.nntdata.transaction.mapper.TransactionMapper;
import com.nntdata.transaction.service.AccountService;
import com.nntdata.transaction.service.ClientRequestProducer;
import com.nntdata.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ClientRequestProducer clientRequestProducer;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public List<ReportResponseDto> generateReport(Long clientId, LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate)) throw new GeneralException("Range date is incorrect.");

        ClientResponseDto clientFind = clientRequestProducer.findClient(clientId);
        if( !clientFind.isStatus() ) throw new GeneralException("Client is not active.");

        List<Account> accounts = accountService.findByClientId(clientId);
        if (accounts.isEmpty() ) throw new GeneralException("Client does not have any account.");
        return accounts.stream()
            .map(account -> {
                List<Transaction> transactions = transactionService.findByAccountAndDates(account.getId(), startDate, endDate);
                return  ReportResponseDto.builder()
                        .client(clientFind.getName())
                        .accountNumber(account.getAccountNumber())
                        .accountType(account.getType().toString())
                        .balance(account.getBalance())
                        .transactionDtoList( transactionMapper.toReportDto(transactions) )
                        .build();
            })
            .toList();

    }

}
