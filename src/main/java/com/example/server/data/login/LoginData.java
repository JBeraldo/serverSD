package com.example.server.data.login;

import com.example.server.base.ResponseData;

public class LoginData extends ResponseData {
    private String token;

    public LoginData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
