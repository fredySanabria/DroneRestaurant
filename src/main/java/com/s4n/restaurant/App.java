package com.s4n.restaurant;

import com.s4n.restaurant.application.DronService;
import com.s4n.restaurant.application.ServiceLocator;
import com.s4n.restaurant.domain.Dron;
import com.s4n.restaurant.repository.IDeliveryRoutesRepository;

import java.io.IOException;
import java.util.List;

/**
 * S4N Challenge APP Starter
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        IDeliveryRoutesRepository routesRepository = ServiceLocator.getInstance().getRoutesRepository();
        DronService dronService = new DronService(routesRepository);
        //List<Dron> dronList = dronService.getDrones();
        dronService.getDrones().stream()
                .map(drone -> dronService.deliveryOrders(drone))
                .forEach(drone -> dronService.writeDronReport(drone));
    }
}
