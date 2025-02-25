package com.btl.menu.service;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static com.btl.menu.constant.Constant.*;

public class LocalStorageService {

    private final Map<String, String> storage = new HashMap<>();
    private final Map<String, Long> cacheTimestamp = new HashMap<>();
    private final Map<String, Long> cacheDuration = new HashMap<>();
    private final ObjectMapper objectMapper;
    private final long CACHE_DURATION = 60 * 1000 * 60 * 3; // 3 hours

    public LocalStorageService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        setDefaultCacheDuration();
    }

    private void setDefaultCacheDuration() {
        cacheDuration.put(ACCOUNT_CACHE_KEY, 60 * 60 * 1000L); // 1 giờ
        cacheDuration.put(CHARACTER_CACHE_KEY, 60 * 60 * 1000L);
        cacheDuration.put(INVENTORY_CACHE_KEY, 3 * 60 * 60 * 1000L); // 3 giờ
        cacheDuration.put(ITEM_CACHE_KEY, 3 * 60 * 60 * 1000L);
    }

    public <T> void put(String key, T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            storage.put(key, json);
            cacheTimestamp.put(key, System.currentTimeMillis());
            Gdx.app.debug(DEBUG, "Put: " + key + ":" + json);
            Gdx.app.debug(DEBUG, "Cache: " + storage.get(key));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        String json = storage.get(key);
        Gdx.app.debug(DEBUG, "Get: " + key + ":" + json);

        if (json == null)
            return null;

        if (cacheTimestamp.get(key) == null
//                || cacheDuration.get(key) == null
                || System.currentTimeMillis() - cacheTimestamp.get(key) > CACHE_DURATION
//                || System.currentTimeMillis() - cacheTimestamp.get(key) > cacheDuration.get(key)
        ) {
            return null;
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T get(String key, TypeReference<T> typeRef) {
        String json = storage.get(key);

        if (json == null)
            return null;

        Long timestamp = cacheTimestamp.get(key);
        if (timestamp == null || System.currentTimeMillis() - timestamp > CACHE_DURATION) {
            return null;
        }

        try {
            return objectMapper.readValue(json, typeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear() {
        storage.clear();
        cacheTimestamp.clear();
    }
}
