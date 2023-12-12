package com.sd.server.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sd.server.Packages.data.request.segment.CreateSegmentRequestData;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="segments")
public class RouteSegment {
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


    public RouteSegment(Long id, String direction, Point origin, Point destination, int distance, String observation) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Segment{" +
                "id=" + id +
                ", direction='" + direction + '\'' +
                ", origin=" + origin +
                ", destination=" + destination +
                ", distance=" + distance +
                ", observation='" + observation + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteSegment segment = (RouteSegment) o;
        return Objects.equals(getId(), segment.getId());
    }
}
