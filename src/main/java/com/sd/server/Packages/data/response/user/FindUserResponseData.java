package com.sd.server.Packages.data.response.user;

import com.sd.server.Base.ResponseData;
import com.sd.server.Models.User;

public class FindUserResponseData extends ResponseData {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FindUserResponseData() {
    }

    public FindUserResponseData(User user) {
        this.user = user;
    }
}
