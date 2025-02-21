package com.utc.api.entity;

import com.utc.api.dto.response.CharacterResponse;
import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "characters")
public class Character extends BaseEntity {

    private String name;
    @ManyToMany(mappedBy = "characters")
    private Set<Account> account = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class _class;
}
