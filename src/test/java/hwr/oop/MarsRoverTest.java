package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverTest {
    @Test
    void turnRoverLeft() {
        int orientation = 0;
        Planet mars = new Planet(10);
        Rover rover = new Rover(mars, orientation);
        rover.turnLeft();
        Assertions.assertThat(orientation).isEqualTo(270);
    }

    @Test
    void turnRoverRight() {
        int orientation = 0;
        Planet mars = new Planet(10);
        Rover rover = new Rover(mars, orientation);
        rover.turnRight();
        Assertions.assertThat(orientation).isEqualTo(90);
    }
}