package com.utc.api.repository;

import com.utc.api.entity.Inventory;
import com.utc.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends BaseRepository<Inventory> {

    @Query("SELECT i " +
               "FROM Inventory i " +
               "WHERE i.account.username = ?#{principal.username}")
    List<Inventory> listByUser();

    @Query("SELECT i " +
               "FROM Inventory i " +
               "WHERE i.account.username = ?#{principal.username} " +
               "AND i.id = :id")
    Inventory findByIdAndUser(@Param("id") Long id);
}
