package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.TransactionType;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {

    private TransactionType type;
    private BigDecimal amount;
    private Long accountId;

}
