package com.nntdata.transaction.mapper;

import com.nntdata.transaction.dto.ReportTransactionDto;
import com.nntdata.transaction.dto.TransactionDto;
import com.nntdata.transaction.dto.TransactionResponseDto;
import com.nntdata.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    TransactionResponseDto toResponseDto(Transaction transaction);
    List<TransactionResponseDto> toListResponseDto(List<Transaction> transactionList);
    Transaction toTransaction(TransactionDto transactionDto);
    List<ReportTransactionDto> toReportDto (List<Transaction> transaction);

}
