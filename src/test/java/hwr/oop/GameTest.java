package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GameTest {
  Game game;

  @BeforeEach
  void instatiateGame() {
    game = new Game();
  }

  @Test
  void gameIsCreatedCorrectlyAndRollsCanBePerformed() {
    assertThat(game).isNotEqualTo(null);

    assertThatNoException().isThrownBy(() -> {
      game.roll(0);
      game.roll(1);
      game.roll(10);
    });
  }

  @Test
  void cannotHitLessThanZeroOrMoreThanTenPins() {
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> game.roll(-1));
    assertThatExceptionOfType(Exception.class).isThrownBy(() -> game.roll(11));
  }

}
