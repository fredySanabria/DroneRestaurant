package com.s4n.restaurant.application;

import com.s4n.restaurant.application.utils.Utils;
import com.s4n.restaurant.domain.CardinalPoint;
import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DronService {
    /**
     * GetDrones return a list of dron's with a default parameters created in a config file
     * @return
     */
    public List<Dron> getDrones() {
        IntStream intStream = IntStream.rangeClosed(0, Integer.parseInt(Utils.getProperty("total-drones"))-1);
        int maxDeliveries = Integer.parseInt(Utils.getProperty("max-deliveries"));
        return intStream
                .mapToObj(number -> new Dron(maxDeliveries, new Coordinate(0, 0, CardinalPoint.N)))
                .collect(Collectors.toList());
    }
}
