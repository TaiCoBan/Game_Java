//package com.utc.api.configuration.init;
//
//import com.utc.api.constants.Constant;
//import com.utc.api.entity.*;
//import com.utc.api.entity.Character;
//import com.utc.api.entity.Class;
//import com.utc.api.repository.*;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static com.utc.api.constants.Constant.*;
//
//@Component
//@Slf4j
//public class ApplicationInitConfiguration implements ApplicationRunner {
//
//    private final AccountRepository accountRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final CharacterRepository characterRepository;
//    private final ItemRepository itemRepository;
//    private final InventoryRepository inventoryRepository;
//    private final ClassRepository classRepository;
//
//    public ApplicationInitConfiguration(AccountRepository accountRepository,
//                                        RoleRepository roleRepository,
//                                        PasswordEncoder passwordEncoder,
//                                        CharacterRepository characterRepository,
//                                        ItemRepository itemRepository,
//                                        InventoryRepository inventoryRepository,
//                                        ClassRepository classRepository) {
//        this.accountRepository = accountRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.characterRepository = characterRepository;
//        this.itemRepository = itemRepository;
//        this.inventoryRepository = inventoryRepository;
//        this.classRepository = classRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Item sword = new Item("Sword");
//        itemRepository.save(sword);
//
//        Inventory inventory = new Inventory();
//        inventory.setName("Base inventory");
//        inventory.setItems(List.of(sword));
//        inventoryRepository.save(inventory);
//
//        Character baseCharacter = new Character("Base Character");
//        characterRepository.save(baseCharacter);
//
//        Class weapon = new Class();
//        weapon.setItems(Set.of(sword));
//        weapon.setCharacters(Set.of(baseCharacter));
//        classRepository.save(weapon);
//
//        Role adminRole = new Role("ADMIN", "This is admin role");
//        roleRepository.save(adminRole);
//        Role userRole = new Role("USER", "This is user role");
//        roleRepository.save(userRole);
//
//        Account admin = new Account();
//        admin.setEmail("admin@utc.com");
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("admin"));
//        admin.setRoles(Set.of(adminRole));
//        admin.setCharacters(Set.of(baseCharacter));
//        admin.setInventories(Set.of(inventory));
//        accountRepository.save(admin);
//    }
//}
