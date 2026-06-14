package cache;

import java.util.HashMap;
import java.util.Map;

public class LoginAttemptCache {

    // username -> failed attempts
    private static final Map<String, Integer> cache = new HashMap<>();

    public static int get(String username) {
        return cache.getOrDefault(username, 0);
    }

    public static void increase(String username) {
        cache.put(username, get(username) + 1);
    }

    public static void reset(String username) {
        cache.remove(username);
    }
}
