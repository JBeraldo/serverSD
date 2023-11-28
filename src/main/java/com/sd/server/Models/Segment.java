package com.sd.server.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Packages.data.request.segment.CreateSegmentRequestData;
import jakarta.persistence.*;

@Entity
@Table(name="segments")
public class Segment {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("direcao")
    String direction;
    @ManyToOne
    @JoinColumn(name = "origin_id")
    @JsonProperty("ponto_origem")
    Point origin;
    @ManyToOne()
    @JoinColumn(name = "destination_id")
    @JsonProperty("ponto_destino")
    Point destination;
    @JsonProperty("distancia")
    int distance;
    @Column
    @JsonProperty("obs")
    String observation;

    public Segment(Long id, String direction, Point origin, Point destination, int distance, String observation) {
        this.id = id;
        this.direction = direction;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.observation = observation;
    }

    public Segment(CreateSegmentRequestData data) {
        this.direction = data.getSegment().getDirection();
        this.origin = data.getSegment().getOrigin();
        this.destination = data.getSegment().getDestination();
        this.distance = data.getSegment().getDistance();
        this.observation = data.getSegment().getObservation();
    }

    public Segment() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
