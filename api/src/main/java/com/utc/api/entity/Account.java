package com.utc.api.entity;

import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
    @ManyToMany
    private List<Character> characters = new ArrayList<>();
    @OneToMany
    private List<Inventory> inventories = new ArrayList<>();
}
