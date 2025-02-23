package com.nntdata.transaction.service;

import com.nntdata.transaction.dto.AccountDto;
import com.nntdata.transaction.dto.AccountResponseDto;
import com.nntdata.transaction.entity.Account;
import com.nntdata.common.exception.GeneralException;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountResponseDto save(AccountDto requestAccount) throws GeneralException;
    void updateBalance(BigDecimal actualBalance, Long id);
    void delete(Long id) throws GeneralException;
    AccountResponseDto findById(Long id) throws GeneralException;
    List<AccountResponseDto> findAll() throws GeneralException;
    Account validateAccount(Long id) throws GeneralException;
    List<Account> findByClientId(Long clientId);
}
