package com.bartlot.Server.model;

import java.util.HashMap;
import java.util.Map;

import java.util.Collections;

public class TokenCached {
    private static final Map<Integer, String> tokenMap = new HashMap<>();

    public static void put(Integer id, String token) {
        tokenMap.put(id, token);
    }

    public static String get(Integer id) {
        return tokenMap.get(id);
    }

    public static boolean containsKey(Integer id) {
        return tokenMap.containsKey(id);
    }

    public static void remove(Integer id) {
        tokenMap.remove(id);
    }

    public static Map<Integer, String> getAllTokens() {
        return Collections.unmodifiableMap(tokenMap);
    }
}
