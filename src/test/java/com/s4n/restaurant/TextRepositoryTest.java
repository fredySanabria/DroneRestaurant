package com.s4n.restaurant;

import com.s4n.restaurant.domain.CardinalPoint;
import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;
import com.s4n.restaurant.repository.IDeliveryRoutesRepository;
import com.s4n.restaurant.repository.impl.TextFileRoutesRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextRepositoryTest {
    private IDeliveryRoutesRepository routesRepository;
    List<Coordinate> deliveryRoutes = new ArrayList<>();

    @Before
    public void init(){
        routesRepository = new TextFileRoutesRepository();
        deliveryRoutes.add(new Coordinate(-2,4,CardinalPoint.W));
        deliveryRoutes.add(new Coordinate(-1,3,CardinalPoint.S));
        deliveryRoutes.add(new Coordinate(0,0,CardinalPoint.W));
    }


    @Test
    public void shouldRetrieveListFromFile() throws IOException {
        List<String> listInstructions = routesRepository.getDeliveryInstructions(
                new Dron(1,3,
                        new Coordinate(0,0, CardinalPoint.N)));
        assertNotNull(listInstructions);
        assertEquals(3, listInstructions.size());
    }
    @Test
    public void shouldCreateFileReport() throws IOException {
        Dron droneTest = new Dron(1, 3,new Coordinate(0, 0, CardinalPoint.N));
        droneTest.setDeliveryList(deliveryRoutes);
        routesRepository.writeDeliveryReport(droneTest);
    }
}
