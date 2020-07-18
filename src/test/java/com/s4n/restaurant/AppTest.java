package com.s4n.restaurant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Properties prop = new Properties();
    int totalDrones;
    @Before
    public void init(){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            prop.load(input);
            totalDrones = Integer.parseInt(prop.getProperty("total-drones"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void shouldCreateDronSuccessfully(){
        List<Dron> restaurantDronList = new ArrayList<>(totalDrones);
        Coordinate coordinatesTest = restaurantDronList.get(0).getCoordinate();
        assertNotNull(restaurantDronList);
    }
}
