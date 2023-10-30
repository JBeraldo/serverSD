package com.sd.server.packages.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.package_data.request.user.DeleteUserRequestData;
import com.sd.server.package_data.request.user.GetUserRequestData;
import com.sd.server.packages.BasePackage;

public class DeleteUserRequestPackage extends BasePackage {
    public DeleteUserRequestPackage(
            @JsonProperty("action") String action,
            @JsonProperty("data") DeleteUserRequestData data,
            @JsonProperty("error") boolean error,
            @JsonProperty("message") String message) {
        super(action, data, error, message);
    }

    @Override
    public DeleteUserRequestData getData() {
        return (DeleteUserRequestData) super.getData();
    }
}
