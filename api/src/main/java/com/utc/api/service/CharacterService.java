package com.utc.api.service;

import com.utc.api.dto.request.UpdateCharacterRequest;
import com.utc.api.dto.response.CharacterResponse;
import com.utc.api.entity.Character;
import com.utc.api.service.base.BaseService;

public interface CharacterService extends BaseService<Character> {

    CharacterResponse updateInf(UpdateCharacterRequest request);
}
