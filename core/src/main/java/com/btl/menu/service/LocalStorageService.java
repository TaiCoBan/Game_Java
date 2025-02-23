package com.btl.menu.service;

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
        cacheDuration.put(ACCOUNT_CACHE_KEY, (long) (60 * 1000 * 60));
        cacheDuration.put(CHARACTER_CACHE_KEY, (long) (60 * 1000 * 60));
        cacheDuration.put(INVENTORY_CACHE_KEY, (long) (60 * 1000 * 60 * 3));
        cacheDuration.put(ITEM_CACHE_KEY, (long) (60 * 1000 * 60 * 3));
    }

    public <T> void put(String key, T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            storage.put(key, json);
            cacheTimestamp.put(key, System.currentTimeMillis());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        String json = storage.get(key);

        if (json == null)
            return null;

        if (cacheTimestamp.get(key) == null
                || cacheDuration.get(key) == null
                || System.currentTimeMillis() - cacheTimestamp.get(key) > CACHE_DURATION
                || System.currentTimeMillis() - cacheTimestamp.get(key) > cacheDuration.get(key)
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
        if (json == null) return null;
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
