package com.sd.server.Packages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRequest {
    private String action;
    public BaseRequest(String action) {
        this.action = action;
    }
    @JsonCreator
    public static BaseRequest create(@JsonProperty("action") String action) {
        return new BaseRequest(action);
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
