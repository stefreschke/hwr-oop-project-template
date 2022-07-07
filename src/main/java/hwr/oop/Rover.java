package hwr.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Rover {
    private Orientation cardinalDirection;
    private Planet mars;
    private Position roverPosition;
    private String roverMessage;
    public Rover(Planet mars, Orientation cardinalDirection, Position roverPosition) {
        this.cardinalDirection = cardinalDirection;
        this.mars = mars;
        this.roverPosition = roverPosition;
    }

    Orientation getOrientation() {

        return cardinalDirection;
    }

    Position getRoverPosition() {
        return roverPosition;
    }

    int getYPosition() {

        return roverPosition.getYCoordinate();
    }

    int getXPosition() {

        return roverPosition.getXCoordinate();
    }

    void turnLeft() {
        if (cardinalDirection == Orientation.N) {
            this.cardinalDirection = Orientation.W;
        }
        else if (cardinalDirection == Orientation.E){
            this.cardinalDirection = Orientation.N;
        }
        else if (cardinalDirection == Orientation.S) {
            this.cardinalDirection = Orientation.E;
        }
        else if (cardinalDirection == Orientation.W) {
            this.cardinalDirection = Orientation.S;
        }
    }

    void turnRight() {
        if (cardinalDirection == Orientation.N) {
            this.cardinalDirection = Orientation.E;
        }
        else if (cardinalDirection == Orientation.E){
            this.cardinalDirection = Orientation.S;
        }
        else if (cardinalDirection == Orientation.S) {
            this.cardinalDirection = Orientation.W;
        }
        else if (cardinalDirection == Orientation.W) {
            this.cardinalDirection = Orientation.N;
        }
    }

    String getRoverMessage() {
        return roverMessage;
    }

    void roverChangeInPosition(int xChange, int yChange) {
        Position frontPosition;
        FieldType frontFieldType;
        int roverXCoordinate = roverPosition.getXCoordinate();
        int roverYCoordinate = roverPosition.getYCoordinate();
        int roverWrappedX = mars.getPlanetDiameter() - (roverXCoordinate + 1);
        boolean xBoundaries = (roverXCoordinate > 0) && (roverXCoordinate < mars.getPlanetDiameter() - 1);
        boolean yBoundaries = (roverYCoordinate > 0) && (roverYCoordinate < mars.getPlanetDiameter() - 1);

        if (xBoundaries && yBoundaries) {
            frontPosition = new Position(roverPosition.getXCoordinate() + xChange, roverPosition.getYCoordinate() + yChange);
            frontFieldType = mars.getFieldType(frontPosition);
            if (frontFieldType instanceof Plain) {
                this.roverPosition = frontPosition;
                this.roverMessage = "OK";
            }
            if (frontFieldType instanceof Martian) {
                this.roverMessage = "Watch out Martian";
            }
        }

        else if (roverYCoordinate == 0 && cardinalDirection == Orientation.N) {
            frontPosition = new Position(roverWrappedX, roverYCoordinate);
            frontFieldType = mars.getFieldType(frontPosition);
            if (frontFieldType instanceof Plain) {
                this.roverPosition = frontPosition;
                this.cardinalDirection = Orientation.S;
                this.roverMessage = "OK";
            }
            if (frontFieldType instanceof Martian) {
                this.roverMessage = "Watch out Martian";
            }
        }

        else if (roverYCoordinate == mars.getPlanetDiameter() - 1 && cardinalDirection == Orientation.S) {
            frontPosition = new Position(roverWrappedX, roverYCoordinate);
            frontFieldType = mars.getFieldType(frontPosition);
            if (frontFieldType instanceof Plain) {
                this.roverPosition = frontPosition;
                this.cardinalDirection = Orientation.N;
                this.roverMessage = "OK";
            }
            if (frontFieldType instanceof Martian) {
                this.roverMessage = "Watch out Martian";
            }
        }

        else if (roverXCoordinate == 0 && cardinalDirection == Orientation.W) {
            frontPosition = new Position(mars.getPlanetDiameter() - 1, roverYCoordinate);
            frontFieldType = mars.getFieldType(frontPosition);
            if (frontFieldType instanceof Plain) {
                this.roverPosition = frontPosition;
                this.roverMessage = "OK";
            }
            if (frontFieldType instanceof Martian) {
                this.roverMessage = "Watch out Martian";
            }
        }

        else if (roverXCoordinate == mars.getPlanetDiameter() - 1 && cardinalDirection == Orientation.E) {
            frontPosition = new Position(0, roverYCoordinate);
            frontFieldType = mars.getFieldType(frontPosition);
            if (frontFieldType instanceof Plain) {
                this.roverPosition = frontPosition;
                this.roverMessage = "OK";
            }
            if (frontFieldType instanceof Martian) {
                this.roverMessage = "Watch out Martian";
            }
        }

        else {
            this.roverMessage = "Error Rover Died";
        }
    }

    void moveForward() {
        if (cardinalDirection == Orientation.N) {
            roverChangeInPosition(0, -1);
        }

        else if (cardinalDirection == Orientation.E) {
            roverChangeInPosition(1,0);
        }

        else if (cardinalDirection == Orientation.S) {
            roverChangeInPosition(0,1);
        }

        else if (cardinalDirection == Orientation.W) {
            roverChangeInPosition(-1, 0);
        }
    }

    void moveBackward() {
        if (cardinalDirection == Orientation.N) {
            roverChangeInPosition(0,1);
        }

        else if (cardinalDirection == Orientation.E) {
            roverChangeInPosition(-1, 0);
        }

        else if (cardinalDirection == Orientation.S) {
            roverChangeInPosition(0, -1);
        }

        else if (cardinalDirection == Orientation.W) {
            roverChangeInPosition(1, 0);
        }
    }

    void followRoute(String route) {
        List<String> routeSteps = new ArrayList<>(Arrays.asList(route.split(",")));
        for (String step: routeSteps) {
            if (step.equals("r")) {
                this.turnRight();
            }
            else if (step.equals("l")) {
                this.turnLeft();
            }

            else if (step.equals("f")) {
                this.moveForward();
            }

            else if (step.equals("b")) {
                this.moveBackward();
            }
        }
    }
}
