package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class StartGameTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void testMainCanBeCalled() {
    // only for testing
    StartGame startGame = new StartGame();
    System.out.println(startGame);
    assertDoesNotThrow(() -> StartGame.main(new String[] {}));
  }

  @Test
  void testMainCanBeCalledWithNoArguments() {
    StartGame.main(new String[]{});
    assertSoftly(
            softly ->
                    softly.assertThat(outContent.toString()).contains("Kein Command wurde übergeben!"));
  }
}