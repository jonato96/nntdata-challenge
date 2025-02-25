package com.nntdata.transaction.dto;

import com.nntdata.transaction.helper.AccountType;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {

    private Long id;
    private Long clientId;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private boolean status;

}
