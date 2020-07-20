package com.s4n.restaurant.domain;

import java.util.ArrayList;
import java.util.List;

public class Dron {
    private int id;
    private int maxDeliveries;
    private Coordinate actualCoordinate;
    private List<Coordinate> deliveryList;

    public Dron(int id, int maxDeliveries, Coordinate actualCoordinate) {
        this.setId(id);
        this.setMaxDeliveries(maxDeliveries);
        this.setActualCoordinate(actualCoordinate);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxDeliveries() {
        return maxDeliveries;
    }

    public void setMaxDeliveries(int maxDeliveries) {
        this.maxDeliveries = maxDeliveries;
    }

    public Coordinate getActualCoordinate() {
        return actualCoordinate;
    }

    public void setActualCoordinate(Coordinate actualCoordinate) {
        this.actualCoordinate = actualCoordinate;
    }

    public List<Coordinate> getDeliveryList() {
        return deliveryList == null ? new ArrayList<>():deliveryList;
    }

    public void setDeliveryList(List<Coordinate> deliveryList) {
        this.deliveryList = deliveryList;
    }

    /**
     * This method return the coordinate since a specific move:
     * For example: "AN"  move 1 step ahead at North
     * @param move
     * @return
     */
    public Coordinate makeMove(String move, Coordinate actualCoordinate) {
        String moveDirection = move + actualCoordinate.getCardinalPoint();
        switch (moveDirection){
            case "AN":
                actualCoordinate.setY(actualCoordinate.getY() + 1);
                break;
            case "AS":
                actualCoordinate.setY(actualCoordinate.getY() -1);
                break;
            case "AE":
                actualCoordinate.setX(actualCoordinate.getX() + 1);
                break;
            case "AW":
                actualCoordinate.setX(actualCoordinate.getX() -1);
                break;
            case "DN":
            case "IS":
                actualCoordinate.setCardinalPoint(CardinalPoint.E);
                break;
            case "DS":
            case "IN":
                actualCoordinate.setCardinalPoint(CardinalPoint.W);
                break;
            case "DE":
            case "IW":
                actualCoordinate.setCardinalPoint(CardinalPoint.S);
                break;
            case "DW":
            case "IE":
                actualCoordinate.setCardinalPoint(CardinalPoint.N);
                break;
        }
        return new Coordinate(actualCoordinate.getX(),actualCoordinate.getY(),actualCoordinate.getCardinalPoint());
    }
}
