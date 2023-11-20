package com.sd.server.Packages.data.request.point;

import com.sd.server.Base.PackageData;

public class GetPointRequestData extends PackageData {
    private String token;
    public GetPointRequestData(){
    }

    public GetPointRequestData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
