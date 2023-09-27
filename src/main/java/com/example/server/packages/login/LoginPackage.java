package com.example.server.packages.login;

import com.example.server.base.ResponseData;
import com.example.server.package_data.request.login.LoginRequestData;
import com.example.server.packages.BasePackage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_NULL)
public class LoginPackage extends BasePackage {
    public LoginPackage(
            @JsonProperty("action") String action,
            @JsonProperty("data") LoginRequestData data,
            @JsonProperty("error") boolean error,
            @JsonProperty("message") String message) {
        super(action, data, error, message);
    }

    @Override
    public LoginRequestData getData() {
        return (LoginRequestData) super.getData();
    }
}
