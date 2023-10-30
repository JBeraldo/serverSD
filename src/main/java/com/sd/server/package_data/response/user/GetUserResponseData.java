package com.sd.server.package_data.response.user;

import com.sd.server.base.ResponseData;
import com.sd.server.models.User;

import java.util.LinkedList;
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
