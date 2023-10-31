package com.sd.server.Packages.data.request.user;

import com.sd.server.Base.ResponseData;

public class FindSelfUserRequestData extends ResponseData {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FindSelfUserRequestData(String token, Long user_id) {
        this.token = token;
    }
    public FindSelfUserRequestData() {
    }
}

