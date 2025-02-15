package com.utc.api.entity;

import com.utc.api.entity.base.BaseEntity;
import com.utc.api.entity.listener.AccountListener;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AccountListener.class)
public class Account extends BaseEntity {

    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
