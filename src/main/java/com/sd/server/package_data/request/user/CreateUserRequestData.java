package com.sd.server.package_data.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.base.ResponseData;
import com.sd.server.models.User;
import org.mindrot.jbcrypt.BCrypt;

public class CreateUserRequestData extends ResponseData {
    String name;
    String email;
    String password;
    String type;
    String token;

    public CreateUserRequestData() {
    }

    public CreateUserRequestData(User user, String token) {
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        type = user.getType();
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }
}
