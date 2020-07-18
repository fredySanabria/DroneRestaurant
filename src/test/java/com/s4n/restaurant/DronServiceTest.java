package com.s4n.restaurant;

import com.s4n.restaurant.application.DronService;
import com.s4n.restaurant.application.utils.Utils;
import com.s4n.restaurant.domain.Dron;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DronServiceTest {
    private DronService dronService;
    private int totalDrones;

    @Before
    public void init(){
        dronService = new DronService();
        totalDrones = Integer.parseInt(Utils.getProperty("total-drones"));
    }

    @Test
    public void shouldCreateDronListSuccessfully(){
        List<Dron> restaurantDronList = dronService.getDrones();
        assertNotNull(restaurantDronList);
        assertEquals(totalDrones,restaurantDronList.size());
    }
}
