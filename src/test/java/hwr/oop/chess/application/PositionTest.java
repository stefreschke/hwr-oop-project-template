package hwr.oop.chess.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionTest {
  @Test
  void positionZeroZeroIsInvalid() {
    Assertions.assertThatThrownBy(() -> new Position(0, 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid Position");
  }

  @Test
  void positionGGInvalid() {
    Assertions.assertThatThrownBy(() -> new Position('g', 9))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid Position");
  }
}
