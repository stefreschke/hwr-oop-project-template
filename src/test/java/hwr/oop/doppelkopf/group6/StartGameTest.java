package hwr.oop.doppelkopf.group6;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class StartGameTest {
  @Test
  void testMain() {
    StartGame startGame = new StartGame();
    assertDoesNotThrow(() -> StartGame.main(new String[] {}));
  }
}
