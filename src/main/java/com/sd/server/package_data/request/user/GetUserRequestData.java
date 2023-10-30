package com.sd.server.package_data.request.user;

import com.sd.server.base.ResponseData;

public class GetUserRequestData extends ResponseData {
    private String token;
    public GetUserRequestData(){
    }

    public GetUserRequestData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
