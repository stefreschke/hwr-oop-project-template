package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldTest {
    @Test
    void setFieldType_Rock() {
        FieldType crater = new Crater();
        FieldType rock = new Rock();
        Field field = new Field(crater);
        field.setNewFieldType(rock);
    }

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
    void checkIfOverrideEqualsWorksProperly() {
        FieldType martian = new Martian();
        Field martianField = new Field(martian);
        Field martianField2 = new Field(martian);
        Assertions.assertThat(martianField).isEqualTo(martianField2);
    }

    @Test
    void FieldNull_isNotEqualToActualField() {
        FieldType fieldType = new Rock();
        Field actualField = new Field(fieldType);
        Field nullField = null;
        boolean comparisonResult = actualField.equals(nullField);
        Assertions.assertThat(comparisonResult).isFalse();
    }

    @Test
    void objectIsEqualToItsSelf() {
        FieldType fieldType = new Rock();
        Field field = new Field(fieldType);
        boolean comparisonResult = field.equals(field);
        Assertions.assertThat(comparisonResult).isTrue();
    }

    @Test
    void twoFieldTypeInstances_haveSameHashCodes() {
        FieldType fieldType = new Rock();
        Field rockFieldOne = new Field(fieldType);
        Field rockFieldTwo = new Field(fieldType);
        int hashCodeRockFieldOne = rockFieldOne.hashCode();
        int hashCodeRockFieldTwo = rockFieldTwo.hashCode();
        boolean hashComparisonResult = hashCodeRockFieldOne == hashCodeRockFieldTwo;
        Assertions.assertThat(hashComparisonResult).isTrue();
    }
}
