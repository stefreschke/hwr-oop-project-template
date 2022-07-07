package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MarsRoverTest {
    Planet mars;
    Rover marsRover;

    @BeforeEach
    void setup() {
        Orientation cardinalDirection = Orientation.N;
        int xStartPositionRover = 4;
        int yStartPositionRover = 4;
        Position roverPosition = new Position(xStartPositionRover,yStartPositionRover);
        mars = new Planet(10, roverPosition);
        marsRover = new Rover(mars, cardinalDirection, roverPosition);
    }

    @Test
    void turnRoverLeft() {
        marsRover.turnLeft();
        Assertions.assertThat(marsRover.getOrientation()).isEqualTo(Orientation.W);
    }

    @Test
    void turnRoverRight() {
        marsRover.turnRight();
        Assertions.assertThat(marsRover.getOrientation()).isEqualTo(Orientation.E);
    }
    @Test
    void moveRoverForward() {
        marsRover.moveForward();
        int roverYPosition = marsRover.getYPosition();
        Assertions.assertThat(roverYPosition).isEqualTo(3);
    }
    @Test
    void rotateLeft_moveBackwards() {
        int roverXPosition = marsRover.getXPosition();
        marsRover.turnLeft();
        marsRover.moveBackward();
        int roverXPosition_2 = marsRover.getXPosition();
        Assertions.assertThat(roverXPosition_2).isEqualTo(5);
    }

    @Test
    void moveAlongRouteToDestination_getFinalPosition() {
        String route = "ffrbllf";
        marsRover.followRoute(route);
        boolean correctPosition = (marsRover.getXPosition() == 2) && (marsRover.getYPosition() == 2);
        Assertions.assertThat(correctPosition).isTrue();
    }

    @Test
    void wrappingAtUpperRightCorner_getNewPosition() {
        Orientation cardinalDirection = Orientation.N;
        int xStartPositionRover = 9;
        int yStartPositionRover = 0;
        Position roverPosition = new Position(xStartPositionRover, yStartPositionRover);
        mars = new Planet(10, roverPosition);
        marsRover = new Rover(mars, cardinalDirection, roverPosition);
        marsRover.moveForward();
        boolean correctPosition = (marsRover.getXPosition() == 0) && (marsRover.getYPosition() == 0);
        Assertions.assertThat(correctPosition).isTrue();
    }

    @Test
    void wrappingAtUpperRightCorner_getNewOrientation() {
        Orientation cardinalDirection = Orientation.N;
        int xStartPositionRover = 9;
        int yStartPositionRover = 0;
        Position roverPosition = new Position(xStartPositionRover, yStartPositionRover);
        mars = new Planet(10, roverPosition);
        marsRover = new Rover(mars, cardinalDirection, roverPosition);
        marsRover.moveForward();
        Assertions.assertThat(marsRover.getOrientation()).isEqualTo(Orientation.S);
    }
}