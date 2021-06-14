package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {

    Coordinate cord1;
    Coordinate cord2;
    Coordinate cord3;
    Coordinate cord4;
    @BeforeEach
    public void setUp() throws Exception {
        cord1=new Coordinate(1,2);
        cord2=new Coordinate(2,2);
        cord3=new Coordinate(1,2);
        cord4=new Coordinate(4,6);

    }

    @Test
    void distance() {
        assertEquals(cord1.distance(cord3),0);
        assertEquals(cord3.distance(cord1),0);
        assertEquals(cord1.distance(cord1),0);
        assertEquals(cord3.distance(cord4),5);

    }
}