package com.utc.api.entity;

import com.utc.api.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    private int baseHealth;
    private int baseAttack;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Class> subClasses;
    @OneToMany
    private Set<Character> characters;
    @OneToMany
    private Set<Item> items;
}
