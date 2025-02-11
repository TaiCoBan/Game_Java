package com.utc.api.repository;

import com.utc.api.entity.Account;
import com.utc.api.repository.base.BaseRepository;

public interface AccountRepository extends BaseRepository<Account> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
