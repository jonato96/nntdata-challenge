package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class ReportTransactionDto {

    private LocalDate date;
    private TransactionType type;
    private BigDecimal beforeBalance;
    private BigDecimal amount;
    private BigDecimal afterBalance;

}
