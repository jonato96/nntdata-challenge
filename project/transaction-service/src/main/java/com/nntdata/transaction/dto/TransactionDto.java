package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {

    private TransactionType type;
    private BigDecimal amount;
    private Long accountId;

}
