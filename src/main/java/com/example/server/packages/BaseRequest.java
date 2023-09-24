package com.example.server.packages;

import com.example.server.base.ResponseData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

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
