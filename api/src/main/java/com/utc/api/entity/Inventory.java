package com.utc.api.entity;

import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory extends BaseEntity {

    private String name;
    private int quantity;
    @ManyToMany
    @JoinTable(
        name = "inventory_item",
        joinColumns = @JoinColumn(name = "inventory_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
