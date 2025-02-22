package com.utc.api.entity;

import com.utc.api.dto.response.ClassResponse;
import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Class extends BaseEntity {

    private String name;
    private float baseHealth;
    private float baseAttack;
    @OneToMany(mappedBy = "_class")
    private Set<Character> characters;
    @OneToMany(mappedBy = "_class")
    private Set<Item> items;
}
