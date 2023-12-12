package com.sd.server.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Packages.data.request.segment.CreateSegmentRequestData;
import jakarta.persistence.*;

import java.util.Objects;

public class RouteSegment {

    @JsonProperty("direcao")
    String direction;
    @JsonProperty("ponto_origem")
    Point origin;
    @JsonProperty("ponto_destino")
    Point destination;
    @JsonProperty("distancia")
    int distance;
    @JsonProperty("obs")
    String observation;


    public RouteSegment(Long id, String direction, Point origin, Point destination, int distance, String observation) {
        this.direction = direction;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.observation = observation;
    }

    public RouteSegment(Segment data) {
        this.direction = data.getDirection();
        this.origin = data.getOrigin();
        this.destination = data.getDestination();
        this.distance = data.getDistance();
        this.observation = data.getObservation();
    }

    public RouteSegment() {
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

    @Override
    public String toString() {
        return "Segment{" +
                ", direction='" + direction + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", distance=" + distance +
                ", observation='" + observation + '\'' +
                '}';
    }

}
