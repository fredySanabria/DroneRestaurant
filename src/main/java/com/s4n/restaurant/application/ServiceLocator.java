package com.s4n.restaurant.application;

import com.s4n.restaurant.repository.IDeliveryRoutesRepository;
import com.s4n.restaurant.repository.impl.TextFileRoutesRepository;

import java.io.IOException;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    private final IDeliveryRoutesRepository routesRepository;

    private ServiceLocator() throws IOException {
        routesRepository = new TextFileRoutesRepository();
    }
    public static ServiceLocator getInstance() throws IOException {
        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }
    public IDeliveryRoutesRepository getRoutesRepository(){
        return routesRepository;
    }
}
