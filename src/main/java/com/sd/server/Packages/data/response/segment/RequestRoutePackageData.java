package com.sd.server.Packages.data.response.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;
import com.sd.server.Models.RouteSegment;

import java.util.List;
public class RequestRoutePackageData extends PackageData {
    @JsonProperty("segmentos")
    public List<RouteSegment> segments;

    public RequestRoutePackageData(List<RouteSegment> segments) {
        this.segments = segments;
    }

    public RequestRoutePackageData() {
    }

    public List<RouteSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<RouteSegment> segments) {
        this.segments = segments;
    }
}
