package com.example.testapp.security;

public class JWTTokenSuccessResponse {

    private String token;

    public JWTTokenSuccessResponse(String token) {

        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
