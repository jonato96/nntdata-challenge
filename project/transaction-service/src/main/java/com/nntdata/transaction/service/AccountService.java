package com.nntdata.transaction.service;

import com.nntdata.transaction.dto.AccountDto;
import com.nntdata.transaction.dto.AccountResponseDto;
import com.nntdata.transaction.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountResponseDto save(AccountDto requestAccount);
    void updateBalance(BigDecimal actualBalance, Long id);
    void delete(Long id);
    AccountResponseDto findById(Long id);
    List<AccountResponseDto> findAll();
    Account validateAccount(Long id);
    List<Account> findByClientId(Long clientId);
}
