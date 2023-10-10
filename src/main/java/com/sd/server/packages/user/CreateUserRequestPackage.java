package com.sd.server.packages.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.package_data.request.user.CreateUserRequestData;
import com.sd.server.packages.BasePackage;

public class CreateUserRequestPackage extends BasePackage {

    public CreateUserRequestPackage(
            @JsonProperty("action") String action,
            @JsonProperty("data") CreateUserRequestData data,
            @JsonProperty("error") boolean error,
            @JsonProperty("message") String message) {
        super(action, data, error, message);
    }

    @Override
    public CreateUserRequestData getData() {
        return (CreateUserRequestData) super.getData();
    }
}
