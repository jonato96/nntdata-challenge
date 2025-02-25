package com.nntdata.transaction.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportResponseDto {

    private String client;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private List<ReportTransactionDto> transactionDtoList;

}
