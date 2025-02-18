package com.utc.api.mapper;

import com.utc.api.dto.request.UpdateAccountRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResponse toAccountResponse(Account account);
    Account toAccount(UpdateAccountRequest request);
}
