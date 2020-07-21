package com.s4n.restaurant;

import com.s4n.restaurant.application.DronService;
import com.s4n.restaurant.application.ServiceLocator;
import com.s4n.restaurant.exception.OutOfRangeException;
import com.s4n.restaurant.repository.IDeliveryRoutesRepository;


/**
 * S4N Challenge APP Starter
 *
 */
public class App 
{
    public static void main( String[] args ) throws OutOfRangeException {
        IDeliveryRoutesRepository routesRepository = ServiceLocator.getInstance().getRoutesRepository();
        DronService dronService = new DronService(routesRepository);
        dronService.getDrones().stream()
                .map(dronService::deliveryOrders)
                .forEach(dronService::writeDronReport);
    }
}
