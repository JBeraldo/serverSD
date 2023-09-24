package com.example.server.repositories;

import com.example.server.packages.BasePackage;
import com.example.server.packages.login.LoginPackage;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoginRepository {
    public BasePackage login(String action,String login_request) throws JsonProcessingException {
        BasePackage request = BasePackage.fromJson(login_request, LoginPackage.class);
        System.out.println(request.toJson());
        return new BasePackage(action,null,false,null);
    }
    public void logout(){}

}
