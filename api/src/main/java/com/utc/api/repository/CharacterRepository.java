package com.utc.api.repository;

import com.utc.api.entity.Character;
import com.utc.api.repository.base.BaseRepository;

public interface CharacterRepository extends BaseRepository<Character> {
  Long id(Long id);
}
