package com.example.testapp.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/v1/auth/**";
    public static final String SECRET = "SaCuRa";
    public static final String TOKEN_PREFIX = "Bearer_";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 3_600_000; // 10 часов
}
