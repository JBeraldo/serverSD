package com.sd.server.package_data.request.login;

import com.sd.server.base.ResponseData;

public class LoginRequestData extends ResponseData {
    private String email;
    private String password;

    public LoginRequestData(){
    }

    public LoginRequestData(String email, String password) {
        this.email = email;
        this.password = password;
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
}
