package com.utc.api.repository;

import com.utc.api.entity.Account;
import com.utc.api.entity.Character;
import com.utc.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends BaseRepository<Character> {

    @Query("SELECT c " +
               "FROM Character c " +
               "WHERE c.id = :id " +
               "AND c.account.username = ?#{principal.username}")
    Optional<Character> findByIdAndUser(@Param("id") Long id);

    @Query("SELECT c " +
               "FROM Character c " +
               "WHERE c.account.username = ?#{principal.username}")
    List<Character> findAllByUser();

    Long id(Long id);
}
