package com.sd.server.Packages.data.request.segment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Base.PackageData;
import com.sd.server.Models.Point;

public class RequestRouteRequestData extends PackageData {
    @JsonProperty("ponto_origem")
    Point origin;

    @JsonProperty("ponto_destino")
    Point destiny;

    public RequestRouteRequestData() {
    }

    public RequestRouteRequestData(Point origin, Point destiny) {
        this.origin = origin;
        this.destiny = destiny;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestiny() {
        return destiny;
    }

    public void setDestiny(Point destiny) {
        this.destiny = destiny;
    }

}
