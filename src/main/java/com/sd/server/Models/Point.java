package com.sd.server.Models;

import com.sd.server.Packages.data.request.point.CreatePointRequestData;
import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String observation;

    public Point() {
    }

    public Point(Long id, String name, String observation) {
        this.id = id;
        this.name = name;
        this.observation = observation;
    }

    public Point(CreatePointRequestData requestData) {
        this.name = requestData.getName();
        this.observation = requestData.getObs();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
