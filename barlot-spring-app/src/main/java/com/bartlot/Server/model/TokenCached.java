package com.bartlot.Server.model;

import java.util.HashMap;
import java.util.Map;

public class TokenCached {
    private static final Map<Integer, String> cache = new HashMap<>();

    public static void put(int userId, String token) {
        cache.put(userId, token);
    }

    public static String get(int userId) {
        return cache.get(userId);
    }

    public static void remove(int userId) {
        cache.remove(userId);
    }

    // Getter for cache map
    public static Map<Integer, String> getCache() {
        return cache;
    }

    // Setter for cache map
    public static void setCache(Map<Integer, String> newCache) {
        cache.clear();
        cache.putAll(newCache);
    }
}
