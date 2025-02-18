package com.utc.api.configuration.init;

import com.utc.api.constants.Constant;
import com.utc.api.entity.Account;
import com.utc.api.entity.Role;
import com.utc.api.repository.AccountRepository;
import com.utc.api.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.utc.api.constants.Constant.*;

@Component
@Slf4j
public class ApplicationInitConfiguration implements ApplicationRunner {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationInitConfiguration(AccountRepository accountRepository,
                                        RoleRepository roleRepository,
                                        PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleInit();
        defaultAdminAccountInit();
        defaultUserAccountInit();

    }

    private void defaultUserAccountInit() {
        String email = "user@gmail.com";
        String username = "user1234";
        String password = "user1234";

        if (accountRepository.existsByEmail(email)
        || accountRepository.existsByUsername(username)) {
            return;
        }

        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.getRoles().add(roleRepository.findByName(ROLE_USER));

        accountRepository.save(account);
        log.warn("User account initialized with default email, username and password");
    }

    private void defaultAdminAccountInit() {
        String email = "admin@gmail.com";
        String username = "admin";
        String password = "admin";

        if (accountRepository.existsByEmail(email)
        || accountRepository.existsByUsername(username)) {
            return;
        }

        Account adminAccount = new Account();
        adminAccount.setEmail(email);
        adminAccount.setUsername(username);
        adminAccount.setPassword(passwordEncoder.encode(password));
        adminAccount.getRoles().add(roleRepository.findByName(ROLE_ADMIN));

        accountRepository.save(adminAccount);
        log.warn("Admin account initialized with default email, username and password");
    }

    private void roleInit() {
        if (!roleRepository.existsByName(ROLE_ADMIN)) {
            Role adminRole= new Role(ROLE_ADMIN, "This is the admin role");
            roleRepository.save(adminRole);
            log.warn("Admin role inserted");
        }

        if (!roleRepository.existsByName(ROLE_USER)) {
            Role userRole= new Role(ROLE_USER, "This is the user role");
            roleRepository.save(userRole);
            log.warn("User role inserted");
        }

    }
}
