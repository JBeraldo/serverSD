package com.sd.server.packages.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.package_data.request.user.GetUserRequestData;
import com.sd.server.packages.BasePackage;

public class GetUserRequestPackage extends BasePackage {
    public GetUserRequestPackage(
            @JsonProperty("action") String action,
            @JsonProperty("data") GetUserRequestData data,
            @JsonProperty("error") boolean error,
            @JsonProperty("message") String message) {
        super(action, data, error, message);
    }

    @Override
    public GetUserRequestData getData() {
        return (GetUserRequestData) super.getData();
    }
}
