package com.ck.backend.config;

public class JwtConstants {
    public static final String SECRET = "ThisIsAVeryLongAndSecureSecretKeyForHS512AlgorithmThatIsAtLeast64BytesLongAndRandomlyGenerated";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
