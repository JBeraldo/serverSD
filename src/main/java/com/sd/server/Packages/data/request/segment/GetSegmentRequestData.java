package com.sd.server.Packages.data.request.segment;

import com.sd.server.Base.PackageData;

public class GetSegmentRequestData extends PackageData {
    private String token;
    public GetSegmentRequestData(){
    }

    public GetSegmentRequestData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
