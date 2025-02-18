package com.utc.api.mapper;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    AccountResponse toAccountResponse(Account account);
    Account toAccount(ChangePasswordRequest request);
}
