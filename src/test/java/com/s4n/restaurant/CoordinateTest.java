package com.s4n.restaurant;

import com.s4n.restaurant.domain.CardinalPoint;
import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.exception.OutOfRangeException;
import org.junit.Test;

import java.io.IOException;

public class CoordinateTest {
    @Test(expected = OutOfRangeException.class)
    public void shouldThrowOutOfRangeException() throws IOException {
        Coordinate coordinate = new Coordinate(-12,12, CardinalPoint.W);
    }
}
