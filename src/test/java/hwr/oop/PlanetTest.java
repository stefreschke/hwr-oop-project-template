package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanetTest {
    @Test
    void generateSamplePlanet() {
        int planetSize = 10;
        Planet mars = new Planet(planetSize);
        Assertions.assertThat(mars.getArea()).isEqualTo(100);
    }
}
