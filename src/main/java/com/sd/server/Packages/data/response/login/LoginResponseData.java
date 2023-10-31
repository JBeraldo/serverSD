package com.sd.server.Packages.data.response.login;

import com.sd.server.Base.ResponseData;

public class LoginResponseData extends ResponseData {
    private String token;

    public LoginResponseData(String token) {
        this.token = token;
    }

    public LoginResponseData() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
