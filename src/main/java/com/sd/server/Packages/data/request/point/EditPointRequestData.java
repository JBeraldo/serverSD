package com.sd.server.Packages.data.request.point;

import com.sd.server.Base.PackageData;
import com.sd.server.Models.Point;

public class EditPointRequestData extends PackageData {
    String token;
    Long ponto_id;
    String name;
    String obs;

    public EditPointRequestData() {
    }

    public EditPointRequestData(Point point, String token){
        this.token = token;
        ponto_id = point.getId();
        name = point.getName();
        obs = point.getObservation();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
