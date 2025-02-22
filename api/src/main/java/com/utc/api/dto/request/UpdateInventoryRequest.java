package com.utc.api.dto.request;

import com.utc.api.entity.Inventory;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateInventoryRequest {

    @NotBlank
    private Long id;
    private List<Long> items;
}
