package com.utc.api.controller;

import com.utc.api.dto.request.UpdateCharacterRequest;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.dto.response.CharacterResponse;
import com.utc.api.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters/")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("{id}")
    public ApiResponse<?> find(@PathVariable Long id) {
        return ApiResponse
                   .<CharacterResponse>builder()
                   .result(characterService.findById(id))
                   .build();
    }

    @GetMapping
    public ApiResponse<?> findAll() {
        return ApiResponse
                   .<List<CharacterResponse>>builder()
                   .result(characterService.findAll())
                   .build();
    }

    @PutMapping
    public ApiResponse<?> update(@Valid @RequestBody UpdateCharacterRequest request) {
        return ApiResponse
                   .<CharacterResponse>builder()
                   .result(characterService.updateInf(request))
                   .build();
    }
}
