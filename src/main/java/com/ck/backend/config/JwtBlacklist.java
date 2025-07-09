package com.ck.backend.config;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtBlacklist {

    private final Set<String> blacklistedTokens = Collections.synchronizedSet(new HashSet<>());

    public void addToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public void removeToken(String token) {
        blacklistedTokens.remove(token);
    }
}
