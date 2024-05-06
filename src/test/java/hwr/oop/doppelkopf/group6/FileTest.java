package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.io.IOException;
import java.nio.file.Path;


class FileTest {
  private final PrintStream standardErr = System.err;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setErr(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  void tearDown() {
    System.setErr(standardErr);
  }

  private void createTestFileWithGameID(String gameID) {
    try {
      String fileName = "/Users/lukaskarsten/Desktop/test.txt";
      File file = new File(fileName);
      Path path = file.toPath();

      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter writer = new FileWriter(file);
      writer.write(gameID + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void testCreateGame_ExistingGameID() throws IOException {
    String gameID = "42";
    createTestFileWithGameID(gameID);
    StartGame startGame = new StartGame();

    startGame.createGame("420");

    assertThat(outputStreamCaptor.toString()).doesNotContain("Das Spiel existiert bereits");
  }


}
