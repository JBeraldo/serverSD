package com.sd.server.packages.logout;

import com.sd.server.package_data.request.logout.LogoutRequestData;
import com.sd.server.packages.BasePackage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogoutPackage extends BasePackage {
    public LogoutPackage(
            @JsonProperty("action") String action,
            @JsonProperty("data") LogoutRequestData data,
            @JsonProperty("error") boolean error,
            @JsonProperty("message") String message) {
        super(action, data, error, message);
    }

    @Override
    public LogoutRequestData getData() {
        return (LogoutRequestData) super.getData();
    }
}
