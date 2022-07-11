package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    void PositionNull_isNotEqualToActualPosition() {
        Position actualPosition = new Position(0, 0);
        Position nullPosition = null;
        boolean comparisonResult = actualPosition.equals(nullPosition);
        Assertions.assertThat(comparisonResult).isFalse();
    }

    @Test
    void objectIsEqualToItsSelf() {
        Position position = new Position(1, 1);
        boolean comparisonResult = position.equals(position);
        Assertions.assertThat(comparisonResult).isTrue();
    }
}
