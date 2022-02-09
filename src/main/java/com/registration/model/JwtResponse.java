package com.registration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class JwtResponse {
    String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
