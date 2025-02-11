package com.utc.api.entity;

import com.utc.api.constants.Constant;
import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

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
    @ManyToMany
    private Set<Role> roles;
}
