package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MarsRoverTest {
    @Test
    void checkFieldType_forRock() {
        FieldType rock = new Rock();
        Field rockField = new Field(rock);
        Assertions.assertThat(rockField.getFieldType()).isEqualTo(rock);
    }

    @Test
    void checkFieldType_forPlain() {
        FieldType plain = new Plain();
        Field plainField = new Field(plain);
        Assertions.assertThat(plainField.getFieldType()).isEqualTo(plain);
    }

    @Test
    void checkFieldType_forCrater() {
        FieldType crater = new Crater();
        Field craterField = new Field(crater);
        Assertions.assertThat(craterField.getFieldType()).isEqualTo(crater);
    }

    @Test
    void checkFieldType_forMartian() {
        FieldType martian = new Martian();
        Field martianField = new Field(martian);
        Assertions.assertThat(martianField.getFieldType()).isEqualTo(martian);
    }


    @Test
    void generateSamplePlanet() {
        int planetSize = 10;
        Planet mars = new Planet(planetSize);
        Assertions.assertThat(mars.getArea()).isEqualTo(100);
    }

    @Test
    void turnRoverLeft() {
        int orientation = 0;
        Planet mars = new Planet(10);
        Rover rover = new Rover(mars, orientation);
        Rover.turnLeft();
        Assertions.assertThat(orientation).isEqualTo(270);
    }

    @Test
    void turnRoverRight() {
        int orientation = 0;
        Planet mars = new Planet(10);
        Rover rover = new Rover(mars, orientation);
        Rover.turnRight();
        Assertions.assertThat(orientation).isEqualTo(90);
    }
}