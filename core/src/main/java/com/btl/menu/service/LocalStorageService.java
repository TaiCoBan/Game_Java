package com.btl.menu.service;

import java.util.HashMap;
import java.util.Map;

public class LocalStorageService {

    private static final Map<String, Object> storage = new HashMap<String, Object>();
    private static final long CACHE_DURATION = 60 * 1000 * 60 * 3; // 3 hours
    private static final Map<String, Long> cacheTimestamp = new HashMap<>();

    public static void put(String key, Object value) {

    }

    public static Object get(String key) {

        return null;
    }

    public static void clear() {
        storage.clear();
        cacheTimestamp.clear();
    }
}
