package com.utc.api.service.impl;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import com.utc.api.entity.Character;
import com.utc.api.entity.Inventory;
import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.repository.AccountRepository;
import com.utc.api.repository.CharacterRepository;
import com.utc.api.repository.InventoryRepository;
import com.utc.api.repository.RoleRepository;
import com.utc.api.service.AccountService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CharacterRepository characterRepository;
    private final InventoryRepository inventoryRepository;

    public AccountServiceImpl(AccountRepository repository,
                              RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder,
                              CharacterRepository characterRepository,
                              InventoryRepository inventoryRepository) {
        super(repository);
        this.accountRepository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.characterRepository = characterRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public AccountResponse register(RegisterRequest request) {
        Account account = new Account();

        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        accountRepository.save(account);

        Character baseChar = new Character();
        baseChar.setName("Bob");
        baseChar.setAccount(account);

        Inventory baseInv = new Inventory();
        baseInv.setName("Cloth bag");
        baseInv.setQuantity(2);
        baseInv.setAccount(account);

        account.getCharacters().add(baseChar);
        account.getInventories().add(baseInv);

        characterRepository.save(baseChar);
        inventoryRepository.save(baseInv);

        return AccountResponse.from(accountRepository.save(account));
    }

    @Override
    public AccountResponse getProfile() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return AccountResponse.from(accountRepository.findByUsername(username).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND)));
    }

    @Override
    public AccountResponse changePassword(ChangePasswordRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        return AccountResponse.from(update(account));
    }

    @Override
    public void logout(Account account) {
        System.out.println("LOGOUT");
    }

    @Override
    public boolean isExistsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public boolean isExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
