package com.s4n.restaurant.application;

import com.s4n.restaurant.repository.IDeliveryRoutesRepository;
import com.s4n.restaurant.repository.impl.TextFileRoutesRepository;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    private IDeliveryRoutesRepository routesRepository;

    private ServiceLocator(){
        routesRepository = new TextFileRoutesRepository();
    }
    public static ServiceLocator getInstance(){
        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }
    public IDeliveryRoutesRepository getRoutesRepository(){
        return routesRepository;
    }
}
