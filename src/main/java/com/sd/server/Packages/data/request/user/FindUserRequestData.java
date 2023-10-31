package com.sd.server.Packages.data.request.user;

import com.sd.server.Base.ResponseData;

public class FindUserRequestData extends ResponseData {
    private String token;
    private Long user_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public FindUserRequestData(String token, Long user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public FindUserRequestData() {
    }
}

