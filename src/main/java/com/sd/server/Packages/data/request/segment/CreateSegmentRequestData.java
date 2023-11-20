package com.sd.server.Packages.data.request.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;
import com.sd.server.Models.Segment;

public class CreateSegmentRequestData extends PackageData {
    String token;

    @JsonProperty("segmento")
    Segment segment;

    public CreateSegmentRequestData() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

}
