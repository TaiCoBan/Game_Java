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

        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.getRoles().add(roleRepository.findByName(Constant.ROLE_ADMIN));

        accountRepository.save(account);
        log.warn("User account initialized with default email, username and password");
    }

    private void defaultAdminAccountInit() {
        String email = "admin@gmail.com";
        String username = "admin";
        String password = "admin";

        Account adminAccount = new Account();
        adminAccount.setEmail(email);
        adminAccount.setUsername(username);
        adminAccount.setPassword(passwordEncoder.encode(password));
        adminAccount.getRoles().add(roleRepository.findByName(Constant.ROLE_ADMIN));

        accountRepository.save(adminAccount);
        log.warn("Admin account initialized with default email, username and password");
    }

    private void roleInit() {
        Role adminRole= new Role("ADMIN", "This is the admin role");
        Role userRole= new Role("USER", "This is the user role");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        log.warn("Admin role, user role inserted");
    }
}
