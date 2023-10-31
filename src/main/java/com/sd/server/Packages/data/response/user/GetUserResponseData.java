package com.sd.server.Packages.data.response.user;

import com.sd.server.Base.ResponseData;
import com.sd.server.Models.User;

import java.util.List;

public class GetUserResponseData extends ResponseData {

    private List<User> users;

    public GetUserResponseData(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
