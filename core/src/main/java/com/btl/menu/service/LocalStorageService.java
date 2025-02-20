package com.btl.menu.service;

import java.util.HashMap;
import java.util.Map;

public class LocalStorageService {

    private final Map<String, Object> storage = new HashMap<String, Object>();
    private final long CACHE_DURATION = 60 * 1000 * 60 * 3; // 3 hours
    private final Map<String, Long> cacheTimestamp = new HashMap<>();

    public void put(String key, Object value) {

    }

    public Object get(String key) {

        return null;
    }

    public void clear() {
        storage.clear();
        cacheTimestamp.clear();
    }
}
