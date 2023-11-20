package com.sd.server.Packages.data.request.point;

import com.sd.server.Base.PackageData;

public class FindPointRequestData extends PackageData {
    String token;
    Long ponto_id;

    public FindPointRequestData(String token, Long ponto_id) {
        this.token = token;
        this.ponto_id = ponto_id;
    }

    public FindPointRequestData() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPonto_id() {
        return ponto_id;
    }

    public void setPonto_id(Long ponto_id) {
        this.ponto_id = ponto_id;
    }
}
