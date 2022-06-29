package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanetTest {
    @Test
    void generateSamplePlanet() {
        int planetSize = 10;
        int xStartPositionRover = 4;
        int yStartPositionRover = 4;
        Planet mars = new Planet(planetSize, xStartPositionRover, yStartPositionRover);
        Assertions.assertThat(mars.getArea()).isEqualTo(100);
    }
}
