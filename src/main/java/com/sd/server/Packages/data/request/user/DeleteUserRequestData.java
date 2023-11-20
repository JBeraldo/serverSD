package com.sd.server.Packages.data.request.user;

import com.sd.server.Base.PackageData;

public class DeleteUserRequestData extends PackageData {
    private String token;

    private Long user_id;

    public DeleteUserRequestData(String token, Long user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public DeleteUserRequestData() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
