package com.s4n.restaurant;

import com.s4n.restaurant.application.DronService;
import com.s4n.restaurant.domain.CardinalPoint;
import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;
import com.s4n.restaurant.repository.IDeliveryRoutesRepository;
import com.s4n.restaurant.repository.impl.TextFileRoutesRepository;
import com.s4n.restaurant.utils.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DronServiceTest {
    @InjectMocks
    private DronService dronService;
    private int totalDrones;
    private List<Dron> restaurantDronList;

    private IDeliveryRoutesRepository repository;

    @Before
    public void init() {
        repository = mock(TextFileRoutesRepository.class);
        dronService = new DronService(repository);
        totalDrones = Integer.parseInt(Objects.requireNonNull(FileUtils.getProperty("total-drones")));
        restaurantDronList = dronService.getDrones();
    }

    @Test
    public void shouldCreateDronListSuccessfully() {
        assertNotNull(restaurantDronList);
        Assert.assertEquals(totalDrones, restaurantDronList.size());
    }

    @Test
    public void shouldMoveDronInOtherCoordinate() {
        String instructionsLine = "AAAAIAA";
        dronService.makeDelivery(restaurantDronList.get(0), instructionsLine);
        assertNotNull(restaurantDronList.get(0).getActualCoordinate());
        assertEquals(new Coordinate(-2, 4, CardinalPoint.W), restaurantDronList.get(0).getActualCoordinate());
    }

    @Test
    public void shouldExecuteListDeliverySuccessfully() throws IOException {
        List<String> deliveryInstructionList = new ArrayList<>();
        deliveryInstructionList.add("AAAAIAA");
        deliveryInstructionList.add("DDDAIAD");
        deliveryInstructionList.add("AAIADAD");
        when(repository.getDeliveryInstructions(any(Dron.class))).thenReturn(deliveryInstructionList);
        Dron dron = dronService.deliveryOrders(restaurantDronList.get(0));
        assertNotNull(dron.getDeliveryList());
        assertEquals(3, dron.getDeliveryList().size());
    }
}
