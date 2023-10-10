package com.sd.server.package_data.response.login;

import com.sd.server.base.ResponseData;

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
