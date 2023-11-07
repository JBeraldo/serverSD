package com.sd.server.Packages.data.request.user;

import com.sd.server.Base.ResponseData;

public class DeleteSelfUserRequestData extends ResponseData {
    private String token;

    private String email;

    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DeleteSelfUserRequestData() {
    }

    public DeleteSelfUserRequestData(String token, String email, String password) {
        this.token = token;
        this.email = email;
        this.password = password;
    }
}
