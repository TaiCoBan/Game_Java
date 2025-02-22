package com.utc.api.service.impl;

import com.utc.api.dto.request.UpdateInventoryRequest;
import com.utc.api.dto.response.InventoryResponse;
import com.utc.api.entity.Account;
import com.utc.api.entity.Inventory;
import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.repository.AccountRepository;
import com.utc.api.repository.InventoryRepository;
import com.utc.api.repository.ItemRepository;
import com.utc.api.service.InventoryService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl extends BaseServiceImpl<Inventory> implements InventoryService {

    private final InventoryRepository repository;
    private final ItemRepository itemRepository;
    private final AccountRepository accountRepository;

    public InventoryServiceImpl(InventoryRepository repository, ItemRepository itemRepository, AccountRepository accountRepository) {
        super(repository);
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<InventoryResponse> listByUser() {
        return repository.listByUser()
                   .stream()
                   .map(InventoryResponse::from)
                   .toList();
    }

    @Override
    public InventoryResponse findById(Long id) {
        try {
            return InventoryResponse.from(repository.findByIdAndUser(id));
        } catch (Exception e) {
            throw new ApiException(ErrorCode.FORBIDDEN_ERROR);
        }
    }

    @Override
    public InventoryResponse updateByUser(UpdateInventoryRequest request) {
        Inventory inventory = repository.findByIdAndUser(request.getId());
        inventory.setItems(
            request.getItems()
                .stream()
                .map(id -> itemRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND)))
                .collect(Collectors.toList()));
        return InventoryResponse.from(update(inventory));
    }
}
