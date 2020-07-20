package com.s4n.restaurant.repository;

import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;

import java.io.IOException;
import java.util.List;

public interface IDeliveryRoutesRepository{
    List<String> getDeliveryInstructions(Dron dron) throws IOException;
    void writeDeliveryReport(Dron dron) throws IOException;
}
