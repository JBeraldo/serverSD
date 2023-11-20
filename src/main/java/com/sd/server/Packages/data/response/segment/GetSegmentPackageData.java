package com.sd.server.Packages.data.response.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;
import com.sd.server.Models.Segment;

import java.util.List;

public class GetSegmentPackageData extends PackageData {
    @JsonProperty("segmentos")
    private List<Segment> segments;

    public GetSegmentPackageData() {
    }

    public GetSegmentPackageData(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
