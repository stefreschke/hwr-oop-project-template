package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanetTest {
    Planet mars;
    @BeforeEach
    void setup() {
        int planetSize = 10;
        int xStartPositionRover = 4;
        int yStartPositionRover = 4;
        Position roverPosition = new Position(xStartPositionRover, yStartPositionRover);
        mars = new Planet(planetSize, roverPosition);
    }

    @Test
    void generatedSamplePlanet_hasCorrectArea() {
        Assertions.assertThat(mars.getArea()).isEqualTo(100);
    }

    @Test
    void setObstacle_isActualObstacle() {
        FieldType rock = new Rock();
        int xPosition = 3;
        int yPosition = 3;
        Position position = new Position(xPosition, yPosition);
        mars.setObstacle(rock, position);
        Assertions.assertThat(mars.getFieldType(position)).isEqualTo(rock);
    }
}
