package com.sd.server.packages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRequest {
    private String action;
    public BaseRequest(String action) {
        this.action = action;
    }
    @JsonCreator
    public static BaseRequest create(@JsonProperty("action") String action) {
        BaseRequest request = new BaseRequest(action);
        return request;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
