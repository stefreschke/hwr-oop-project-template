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
        mars = new Planet(planetSize, xStartPositionRover, yStartPositionRover);
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
        mars.setObstacle(rock, xPosition, yPosition);
        Assertions.assertThat(mars.getFieldType(xPosition, yPosition)).isEqualTo(rock);
    }
}
