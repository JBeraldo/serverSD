package com.sd.server.Packages.data.response.point;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;
import com.sd.server.Models.Point;

import java.util.List;

public class GetPointPackageData extends PackageData {
    @JsonProperty("pontos")
    private List<Point> points;

    public GetPointPackageData(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
