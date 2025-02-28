package com.nntdata.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReportResponseDto {

    private String client;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private List<ReportTransactionDto> transactionDtoList;

}
