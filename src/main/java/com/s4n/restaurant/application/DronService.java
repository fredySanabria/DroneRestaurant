package com.s4n.restaurant.application;

import com.s4n.restaurant.domain.CardinalPoint;
import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;
import com.s4n.restaurant.repository.IDeliveryRoutesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.s4n.restaurant.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DronService {
    private IDeliveryRoutesRepository routesRepository;
    private static final Logger logger = LoggerFactory.getLogger(DronService.class);

    public DronService(IDeliveryRoutesRepository routesRepository) {
        this.routesRepository = routesRepository;
    }

    /**
     * GetDrones return a list of dron's with a default parameters created in a config file
     * @return
     */
    public List<Dron> getDrones() {
        IntStream intStream = IntStream.rangeClosed(1, Integer.parseInt(FileUtils.getProperty("total-drones")));
        int maxDeliveries = Integer.parseInt(FileUtils.getProperty("max-deliveries"));
        return intStream
                .mapToObj(number -> new Dron(number, maxDeliveries, new Coordinate(0, 0, CardinalPoint.N)))
                .collect(Collectors.toList());
    }

    /**
     * Extract instructions of repository and executes all deliveries
     * @param dron
     * @return
     */
    public Dron deliveryOrders(Dron dron) {

        List<String> routesList = new ArrayList<>();
        try {
            routesList = routesRepository.getDeliveryInstructions(dron);
        } catch (IOException exception) {
            logger.error("Error trying to get instructions for drone {}", dron.getId());
        }
        dron.setDeliveryList(
                routesList.stream()
                .map(route -> makeDelivery(dron,route))
                .collect(Collectors.toList()));
        return dron;
    }

    /**
     * Write report file for a specific dron
     * @param dron
     */
    public void writeDronReport(Dron dron) {
        try {
            routesRepository.writeDeliveryReport(dron);
        } catch (IOException exception) {
            logger.error("Error trying to write report for drone {}", dron.getId());
        }
    }

    /**
     * Execute all the move instructions and returns the last Coordinate
     * @param dron
     * @param instructionsLine
     * @return
     */
    public Coordinate makeDelivery(Dron dron, String instructionsLine) {
        return instructionsLine.chars()
                .mapToObj(character -> String.valueOf((char)character))
                .map(move -> dron.makeMove(move,dron.getActualCoordinate()))
                .reduce((first, second) -> second).get();
    }
}
