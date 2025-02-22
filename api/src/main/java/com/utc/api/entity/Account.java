package com.utc.api.entity;

import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "account")
    private Set<Character> characters = new HashSet<>();
    @OneToMany(mappedBy = "account")
    private Set<Inventory> inventories = new HashSet<>();
}
