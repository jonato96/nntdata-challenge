package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AccountDto {

    private Long id;
    private Long clientId;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private boolean status;

}
