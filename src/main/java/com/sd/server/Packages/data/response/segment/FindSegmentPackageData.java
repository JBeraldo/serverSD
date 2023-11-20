package com.sd.server.Packages.data.response.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;
import com.sd.server.Models.Point;
import com.sd.server.Models.Segment;

public class FindSegmentPackageData extends PackageData {
    @JsonProperty("segmento")
    Segment segment;

    public FindSegmentPackageData(Segment segment) {
        this.segment = segment;
    }

    public FindSegmentPackageData() {
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
}
