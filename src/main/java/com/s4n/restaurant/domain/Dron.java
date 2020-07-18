package com.s4n.restaurant.domain;

public class Dron {
    private int maxDeliveries;
    private Coordinate coordinate;

    public Dron(int maxDeliveries, Coordinate coordinate) {
        this.maxDeliveries = maxDeliveries;
        this.coordinate = coordinate;
    }

    public int getMaxDeliveries() {
        return maxDeliveries;
    }

    public void setMaxDeliveries(int maxDeliveries) {
        this.maxDeliveries = maxDeliveries;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
