package hwr.oop;

import java.util.ArrayList;

class Planet {
    int xStartPositionRover;
    int yStartPositionRover;
    public Planet(int planetSize, int xStartPositionRover, int yStartPositionRover) {
        this.xStartPositionRover = xStartPositionRover;
        this.yStartPositionRover = yStartPositionRover;
    }

    int getArea() {
        return 0;
    }

    void setObstacle(FieldType fieldType, int xPosition, int yPosition) {
    }

    FieldType getFieldType(int xPosition, int yPosition) {
        return null;
    }
}
