package com.utc.api.entity;

import com.utc.api.entity.base.BaseEntity;
import com.utc.api.filter.annotation.Username;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    private String email;
    @Username
    private String username;
    private String password;
}
