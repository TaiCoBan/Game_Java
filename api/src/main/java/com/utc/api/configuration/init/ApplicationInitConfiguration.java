package com.utc.api.configuration.init;

import com.utc.api.constants.Constant;
import com.utc.api.entity.*;
import com.utc.api.entity.Character;
import com.utc.api.entity.Class;
import com.utc.api.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.utc.api.constants.Constant.*;

@Component
@Slf4j
public class ApplicationInitConfiguration implements ApplicationRunner {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CharacterRepository characterRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;
    private final ClassRepository classRepository;

    public ApplicationInitConfiguration(AccountRepository accountRepository,
                                        RoleRepository roleRepository,
                                        PasswordEncoder passwordEncoder,
                                        CharacterRepository characterRepository,
                                        ItemRepository itemRepository,
                                        InventoryRepository inventoryRepository,
                                        ClassRepository classRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.characterRepository = characterRepository;
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
        this.classRepository = classRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleInit();
        defaultAdminAccountInit();
        defaultUserAccountInit();
        characterInit();
        classInit();
        inventoryInit();
        itemInit();
        log.info("Base data inserted");
        relationInit();
        log.info("Relation initialized");
    }

    private void relationInit() {
        // Admin account
        Account admin = accountRepository.findById(1L)
                            .orElseThrow(() -> new EntityNotFoundException("Admin account not found"));

        Inventory adminInventory = inventoryRepository.findById(1L)
                                       .orElseThrow(() -> new EntityNotFoundException("Admin inventory not found"));
        adminInventory.setItems(itemRepository.findAll());
        inventoryRepository.save(adminInventory); // Lưu lại thay đổi

        admin.setInventories(List.of(adminInventory));
        accountRepository.save(admin);

        // User account
        Account user = accountRepository.findById(2L)
                           .orElseThrow(() -> new EntityNotFoundException("User account not found"));

        Inventory userInventory = inventoryRepository.findById(2L)
                                      .orElseThrow(() -> new EntityNotFoundException("User inventory not found"));
        userInventory.setItems(List.of(itemRepository.findById(1L).get()));
        inventoryRepository.save(userInventory);

        user.setInventories(List.of(userInventory));
        accountRepository.save(user);
    }

    private void roleInit() {
        if (!roleRepository.existsByName(ROLE_ADMIN)) {
            Role adminRole = new Role(ROLE_ADMIN, "This is the admin role");
            roleRepository.save(adminRole);
        }

        if (!roleRepository.existsByName(ROLE_USER)) {
            Role userRole = new Role(ROLE_USER, "This is the user role");
            roleRepository.save(userRole);
        }
    }

    private void defaultAdminAccountInit() {
        String email = "admin@gmail.com";
        String username = "admin";
        String password = "admin";

        if (accountRepository.existsByEmail(email) || accountRepository.existsByUsername(username)) {
            return;
        }

        Role adminRole = roleRepository.findByName(ROLE_ADMIN);
//                             .orElseThrow(() -> new IllegalStateException("Admin role not found"));

        Account adminAccount = new Account();
        adminAccount.setEmail(email);
        adminAccount.setUsername(username);
        adminAccount.setPassword(passwordEncoder.encode(password));
        adminAccount.getRoles().add(adminRole);

        accountRepository.save(adminAccount);
    }

    private void defaultUserAccountInit() {
        String email = "user@gmail.com";
        String username = "user1234";
        String password = "user1234";

        if (accountRepository.existsByEmail(email) || accountRepository.existsByUsername(username)) {
            return;
        }

        Role userRole = roleRepository.findByName(ROLE_USER);
//                            .orElseThrow(() -> new IllegalStateException("User role not found"));

        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.getRoles().add(userRole);

        accountRepository.save(account);
    }

    private void characterInit() {
        Character Bob = new Character("Bob");
        Character assassin = new Character("Assassin");
        Character gunner = new Character("Gunner");

        characterRepository.save(Bob);
        characterRepository.save(assassin);
        characterRepository.save(gunner);
    }

    private void itemInit() {
        Item woodSword = new Item("Wood sword");
        Item ironSword = new Item("Iron sword");
        Item gun = new Item("Gun");

        itemRepository.save(woodSword);
        itemRepository.save(ironSword);
        itemRepository.save(gun);
    }

    private void inventoryInit() {
        createInventoryIfNotExists("Cloth bag", 1L);
        createInventoryIfNotExists("Leather bag", 2L);
    }

    private void createInventoryIfNotExists(String name, Long id) {
        if (!inventoryRepository.existsById(id)) {
            Inventory inventory = new Inventory();
            inventory.setId(id); // Chỉ định ID để tránh trùng
            inventory.setName(name);
            inventoryRepository.save(inventory);
        }
    }

    private void classInit() {
        Class human = new Class();
        human.setName("Human");

        Class man = new Class();
        man.setName("Man");

        Class woman = new Class();
        woman.setName("Woman");

        classRepository.save(human);
        classRepository.save(man);
        classRepository.save(woman);
    }
}
