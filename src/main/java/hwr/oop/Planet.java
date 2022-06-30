package hwr.oop;

import java.util.HashMap;
import java.util.Map;

class Planet {
    private int planetDiameter;
    private int xStartPositionRover;
    private int yStartPositionRover;
    private Map<Position, Field> fields = new HashMap<>();
    public Planet(int planetDiameter, int xStartPositionRover, int yStartPositionRover) {
        this.planetDiameter = planetDiameter;
        this.xStartPositionRover = xStartPositionRover;
        this.yStartPositionRover = yStartPositionRover;
        generatePlainFields();
    }

    void generatePlainFields() {
        for (int x = 0; x < planetDiameter; x++) {
            for (int y = 0; y < planetDiameter; y++) {
                Position position = new Position(x, y);
                FieldType plain = new Plain();
                Field field = new Field(plain);
                fields.put(position, field);
            }
        }
    }

    int getArea() {

        return planetDiameter*planetDiameter;
    }

    FieldType getFieldType(int xPosition, int yPosition) {
        Position position = new Position(xPosition, yPosition);
        Field field = fields.get(position);
        FieldType fieldType = field.getFieldType();
        return fieldType;
    }

    void setObstacle(FieldType fieldType, int xPosition, int yPosition) {
        Position position = new Position(xPosition, yPosition);
        Field field = new Field(fieldType);
        fields.replace(position, field);
    }


}
