package com.chatop.api.domain.entity;

public class AuthResponse {
    String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

