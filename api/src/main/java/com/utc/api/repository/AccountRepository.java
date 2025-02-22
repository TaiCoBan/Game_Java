package com.utc.api.repository;

import com.utc.api.entity.Account;
import com.utc.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Account> findByUsername(String username);

    Account findByEmail(String email);
}
