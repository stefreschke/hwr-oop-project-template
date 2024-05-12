package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class FileTest {
  @Test
  void testOS() {
    StartGame startGame = new StartGame();
    startGame.getOperatingSystem();
    assertThat(startGame.getOperatingSystem()).isNotNull().isNotEqualTo("");
  }
}
