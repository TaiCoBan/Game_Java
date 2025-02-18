package com.utc.api.service.impl;

import com.utc.api.entity.Character;
import com.utc.api.repository.CharacterRepository;
import com.utc.api.service.CharacterService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl extends BaseServiceImpl<Character> implements CharacterService {

    public CharacterServiceImpl(CharacterRepository baseRepository) {
        super(baseRepository);
    }
}
