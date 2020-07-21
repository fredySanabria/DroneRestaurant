package com.s4n.restaurant.domain;

import com.s4n.restaurant.exception.OutOfRangeException;
import com.s4n.restaurant.utils.FileUtils;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;
    private CardinalPoint cardinalPoint;

    public Coordinate(int x, int y, CardinalPoint cardinalPoint) throws OutOfRangeException {
        this.x = x;
        this.y = y;
        this.cardinalPoint = cardinalPoint;
        if(validateOutOfRangeCoordinate()){
            throw new OutOfRangeException("Dron is in max allowed coordinate");
        };
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }

    public void setCardinalPoint(CardinalPoint cardinalPoint) {
        this.cardinalPoint = cardinalPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y &&
                cardinalPoint == that.cardinalPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", cardinalPoint=" + cardinalPoint +
                '}';
    }

    /**
     * Validates if Coordinate is out of allowed range
     * @return
     */
    private boolean validateOutOfRangeCoordinate(){
        int maxValue = Integer.parseInt(Objects.requireNonNull(FileUtils.getProperty("max-coordinate-value")));
        return (getX() > maxValue) || (getY() > maxValue);
    }
}
