package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TransactionDto {

    private TransactionType type;
    private BigDecimal amount;
    private Long accountId;

}
