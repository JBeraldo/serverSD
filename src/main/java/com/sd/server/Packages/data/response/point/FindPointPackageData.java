package com.sd.server.Packages.data.response.point;

import com.sd.server.Base.PackageData;
import com.sd.server.Models.Point;

public class FindPointPackageData extends PackageData {
    Point ponto;

    public FindPointPackageData(Point ponto) {
        this.ponto = ponto;
    }

    public FindPointPackageData() {
    }

    public Point getPonto() {
        return ponto;
    }

    public void setPonto(Point ponto) {
        this.ponto = ponto;
    }
}
