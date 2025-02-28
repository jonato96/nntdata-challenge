package com.nntdata.transaction.mapper;

import com.nntdata.transaction.dto.AccountDto;
import com.nntdata.transaction.dto.AccountResponseDto;
import com.nntdata.transaction.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    @Mapping(target = "clientName", source = "clientName")
    AccountResponseDto toResponseDto(Account account, String clientName);
    List<AccountResponseDto> toResponseDtoList(List<Account> accounts);
    Account toAccount(AccountDto accountDto);
}
