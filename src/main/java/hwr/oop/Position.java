package hwr.oop;

class Position {
    private int xCoordinate;
    private int yCoordinate;
    public Position(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    int getXCoordinate() {
        return xCoordinate;
    }
    int getYCoordinate() {
        return yCoordinate;
    }
    // I don't get what's happening here, but it works. Need to find out.
    @Override
    public boolean equals(Object o) {
        Position position = (Position) o;
        return yCoordinate == position.yCoordinate;
    }

    @Override
    public int hashCode() {
        int result = xCoordinate;
        result = 31 * result + yCoordinate;
        return result;
    }
}
