package com.utc.api.service.impl;

import com.utc.api.dto.request.UpdateCharacterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.dto.response.CharacterResponse;
import com.utc.api.entity.Character;
import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.repository.CharacterRepository;
import com.utc.api.service.CharacterService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CharacterServiceImpl extends BaseServiceImpl<Character> implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository repository) {
        super(repository);
        this.characterRepository = repository;
    }

    @Override
    public CharacterResponse updateInf(UpdateCharacterRequest request) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        Character character = characterRepository.findById(request.getId())
                                  .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));

        if (!Objects.equals(username, character.getAccount().getUsername())) {
            throw new ApiException(ErrorCode.FORBIDDEN_ERROR);
        }

        character.setName(request.getName());
        character.setHealth(request.getHealth());
        character.setAttack(request.getAttack());
        return CharacterResponse.from(update(character));
    }
}
