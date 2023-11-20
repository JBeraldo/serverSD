package com.sd.server.Packages.data.request.point;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;

public class DeletePointRequestData extends PackageData {
    String token;
    @JsonProperty("ponto_id")
    Long point_id;

    public DeletePointRequestData() {
    }

    public DeletePointRequestData(String token, Long point_id) {
        this.token = token;
        this.point_id = point_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPoint_id() {
        return point_id;
    }

    public void setPoint_id(Long point_id) {
        this.point_id = point_id;
    }
}
