package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TransactionResponseDto {

    private Long id;
    private LocalDate date;
    private TransactionType type;
    private BigDecimal beforeBalance;
    private BigDecimal amount;
    private BigDecimal afterBalance;
    private boolean status;

}
