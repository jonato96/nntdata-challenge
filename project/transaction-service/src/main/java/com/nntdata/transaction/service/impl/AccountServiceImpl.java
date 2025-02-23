package com.nntdata.transaction.service.impl;

import com.nntdata.transaction.dto.AccountDto;
import com.nntdata.transaction.dto.AccountResponseDto;
import com.nntdata.transaction.dto.client.ClientResponseDto;
import com.nntdata.transaction.entity.Account;
import com.nntdata.common.exception.GeneralException;
import com.nntdata.transaction.mapper.AccountMapper;
import com.nntdata.transaction.repository.AccountRepository;
import com.nntdata.transaction.service.AccountService;
import com.nntdata.transaction.service.ClientRequestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRequestProducer clientRequestProducer;

    @Override
    public AccountResponseDto save(AccountDto requestAccount) {
        ClientResponseDto clientForAccount = clientRequestProducer.findClient(requestAccount.getClientId());
        if ( accountRepository.existsByAccountNumber(requestAccount.getAccountNumber()) )
            throw new GeneralException("Account number is not valid.");
        Account accountToSave = accountMapper.toAccount(requestAccount);
        accountToSave.setStatus(true);
        accountToSave.setClientId(clientForAccount.getId());
        Account accountCreated = accountRepository.save(accountToSave);
        AccountResponseDto accountResponse = accountMapper.toResponseDto(accountCreated);
        accountResponse.setClientName(clientForAccount.getName());
        return accountResponse;
    }

    @Override
    public void updateBalance(BigDecimal actualBalance, Long id) {
        accountRepository.updateBalance(actualBalance, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validateExistsAndIsActive(id);
        accountRepository.inactivateAccount(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDto findById(Long id) {
        Account accountFind = accountRepository.findById(id).orElseThrow( () -> new GeneralException("Account not found with id: " + id) );
        ClientResponseDto clientResponse = clientRequestProducer.findClient(accountFind.getClientId());
        AccountResponseDto accountResponse = accountMapper.toResponseDto(accountFind);
        accountResponse.setClientName(clientResponse.getName());
        return accountResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDto> findAll() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) throw new GeneralException("Accounts not found");
        return accountMapper.toResponseDtoList(accounts);
    }

    @Override
    public Account validateAccount(Long id) {
        validateExistsAndIsActive(id);
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> findByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    private void validateExistsAndIsActive(Long id) {
        boolean result = accountRepository.existsByIdAndStatusTrue(id);
        if (!result) throw new GeneralException("Account not found or is already inactive.");
    }
}
