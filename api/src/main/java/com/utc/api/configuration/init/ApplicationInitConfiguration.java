package com.utc.api.configuration.init;

import com.utc.api.entity.*;
import com.utc.api.entity.Character;
import com.utc.api.entity.Class;
import com.utc.api.repository.*;
import com.utc.api.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class ApplicationInitConfiguration implements ApplicationRunner {

    private final AccountService accountService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CharacterService characterService;
    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final ClassService classService;

    public ApplicationInitConfiguration(AccountService accountService,
                                        RoleService roleService,
                                        PasswordEncoder passwordEncoder,
                                        CharacterService characterService,
                                        ItemService itemService,
                                        InventoryService inventoryService,
                                        ClassService classService) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.characterService = characterService;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.classService = classService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // ITEM
        Item woodSword = new Item();
        woodSword.setName("Wood sword");
        Item ironSword = new Item();
        ironSword.setName("Iron sword");
        Item gun = new Item();
        gun.setName("Gun");

        // INVENTORY
        Inventory clothBag = new Inventory();
        clothBag.setName("Cloth bag");
        clothBag.setQuantity(2);
        clothBag.setItems(List.of(woodSword));
        Inventory leatherBag = new Inventory();
        leatherBag.setName("Leather bag");
        leatherBag.setQuantity(4);
        inventoryService.create(leatherBag);

        // CHARACTER
        Character baseCharacter = new Character();
        baseCharacter.setName("Bob");

        // CLASS
        Class human = new Class();
        human.setName("Human");
        human.setBaseHealth(100);
        human.setBaseAttack(5);
        human.setCharacters(Set.of(baseCharacter));
        Class weapon = new Class();
        weapon.setName("Weapon");
        weapon.setBaseHealth(150);
        weapon.setBaseAttack(10);
        weapon.setItems(Set.of(woodSword, ironSword, gun));
        classService.create(human);
        classService.create(weapon);

        woodSword.set_class(weapon);
        woodSword.setHealth(weapon.getBaseHealth());
        woodSword.setAttack(weapon.getBaseAttack());
        ironSword.set_class(weapon);
        ironSword.setHealth(weapon.getBaseHealth() + 50);
        ironSword.setAttack(weapon.getBaseAttack() + 10);
        gun.set_class(weapon);
        gun.setHealth(weapon.getBaseHealth() + 100);
        gun.setAttack(weapon.getBaseAttack() + 20);
        itemService.create(woodSword);
        itemService.create(ironSword);
        itemService.create(gun);

        baseCharacter.set_class(human);
        baseCharacter.setHealth(human.getBaseHealth());
        baseCharacter.setAttack(human.getBaseAttack());
        characterService.create(baseCharacter);

        // ROLE
        Role adminRole = new Role("ADMIN", "This is admin role");
        roleService.create(adminRole);
        Role userRole = new Role("USER", "This is user role");
        roleService.create(userRole);

        // BASE USER
        Account user = new Account();
        user.setEmail("user@utc.com");
        user.setUsername("baseuser");
        user.setPassword(passwordEncoder.encode("baseuser"));
        user.setRoles(Set.of(userRole));
        user.getCharacters().add(baseCharacter);
        user.getInventories().add(clothBag);
        accountService.create(user);

        baseCharacter.setAccount(user);
        characterService.create(baseCharacter);
        clothBag.setAccount(user);
        inventoryService.create(clothBag);

        // ADMIN
        Account admin = new Account();
        admin.setEmail("admin@utc.com");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(adminRole, userRole));
        accountService.create(admin);
    }
}
