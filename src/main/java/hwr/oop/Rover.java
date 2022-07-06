package hwr.oop;

class Rover {
    private Orientation cardinalDirection;
    private Planet mars;
    private Position roverPosition;
    public Rover(Planet mars, Orientation cardinalDirection, Position roverPosition) {
        this.cardinalDirection = cardinalDirection;
        this.mars = mars;
        this.roverPosition = roverPosition;
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

    Orientation getOrientation() {

        return cardinalDirection;
    }
    Position roverChangeInPosition(int xChange, int yChange) {
        Position frontPosition;
        FieldType frontFieldType;
        frontPosition = new Position(roverPosition.getXCoordinate() + xChange, roverPosition.getYCoordinate() + yChange);
        frontFieldType = mars.getFieldType(frontPosition);
        if (frontFieldType instanceof Plain) {
            return this.roverPosition = frontPosition;
        }
        return this.roverPosition;
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
        Position frontPosition;
        FieldType frontFieldType;
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

    int getYPosition() {

        return roverPosition.getYCoordinate();
    }

    int getXPosition() {

        return roverPosition.getXCoordinate();
    }

    String followRoute(String route) {
        return "0";
    }
}
