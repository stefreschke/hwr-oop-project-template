package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    void turnRoverLeftFourTimes_roverOrientationIsCorrectAtEachStep() {
        Orientation fullRotationLeft[] = {Orientation.W, Orientation.S, Orientation.E, Orientation.N};
        for (Orientation cardinalDirection : fullRotationLeft) {
            marsRover.turnLeft();
            Assertions.assertThat(marsRover.getOrientation()).isEqualTo(cardinalDirection);
        }
    }

    @Test
    void turnRoverRight() {
        marsRover.turnRight();
        Assertions.assertThat(marsRover.getOrientation()).isEqualTo(Orientation.E);
    }

    @Test
    void turnRoverRightFourTimes_roverOrientationIsCorrectAtEachStep() {
        Orientation fullRotationLeft[] = {Orientation.E, Orientation.S, Orientation.W, Orientation.N};
        for (Orientation cardinalDirection : fullRotationLeft) {
            marsRover.turnRight();
            Assertions.assertThat(marsRover.getOrientation()).isEqualTo(cardinalDirection);
        }
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
        String route = "f,f,r,b,l,l,f";
        marsRover.followRoute(route);
        boolean correctPosition = (marsRover.getXPosition() == 2) && (marsRover.getYPosition() == 2);
        Assertions.assertThat(correctPosition).isTrue();
    }
    @Test
    void roverDriveABackwardCircle_checkIfPositionsAreRight() {
        //Orientation circleRoute[] = {Orientation.E, Orientation.S, Orientation.W, Orientation.N};
        Position circleSteps[] = {new Position(4, 5), new Position(3, 5),
                new Position(3, 4), new Position(4, 4)};

        for (Position currentPosition : circleSteps) {
            marsRover.moveBackward();
            marsRover.turnRight();
            Assertions.assertThat(marsRover.getRoverPosition()).isEqualTo(currentPosition);
        }
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

    @Test
    void wrappingAtRightUpperEdge_getNewPosition() {
        Orientation cardinalDirection = Orientation.E;
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
    void wrappingAtRightUpperEdge_getNewOrientation() {
        Orientation cardinalDirection = Orientation.E;
        int xStartPositionRover = 9;
        int yStartPositionRover = 0;
        Position roverPosition = new Position(xStartPositionRover, yStartPositionRover);
        mars = new Planet(10, roverPosition);
        marsRover = new Rover(mars, cardinalDirection, roverPosition);
        marsRover.moveForward();
        Assertions.assertThat(marsRover.getOrientation()).isEqualTo(Orientation.E);
    }

    @Test
    void wrappingAtLeftLowerEdge_getNewOrientation() {
        Orientation cardinalDirection = Orientation.S;
        int xStartPositionRover = 0;
        int yStartPositionRover = 9;
        Position roverPosition = new Position(xStartPositionRover, yStartPositionRover);
        mars = new Planet(10, roverPosition);
        marsRover = new Rover(mars, cardinalDirection, roverPosition);
        marsRover.moveForward();
        Assertions.assertThat(marsRover.getOrientation()).isEqualTo(Orientation.N);
    }

    @Test
    void wrappingAtLeftLowerEdge_getNewPosition() {
        Orientation cardinalDirection = Orientation.W;
        int xStartPositionRover = 0;
        int yStartPositionRover = 9;
        Position roverPosition = new Position(xStartPositionRover, yStartPositionRover);
        mars = new Planet(10, roverPosition);
        marsRover = new Rover(mars, cardinalDirection, roverPosition);
        marsRover.moveForward();
        boolean correctPosition = (marsRover.getXPosition() == 9) && (marsRover.getYPosition() == 9);
        Assertions.assertThat(correctPosition).isTrue();
    }

}