package hwr.oop.doppelkopf.group6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {
  StartGame startGame;
  String filePath = "/Users/lukaskarsten/Desktop/test.txt";
  File file = new File(filePath);
  Path path = file.toPath();

  @BeforeEach
  void setUp() throws IOException {
    startGame = new StartGame();
    // Ensure the file does not exist before each test
    if (Files.exists(path)) {
      Files.delete(path);
    }
  }

  @AfterEach
  void tearDown() throws IOException {
    // Clean up by deleting the file after each test
    if (Files.exists(path)) {
      Files.delete(path);
    }
  }

  @Test
  void testCreateGameWithFileNotExisting() {
    String gameID = "1";
    String[] args = {"create", gameID};

    // Capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);

    // Verify the file was created
    assertThat(Files.exists(path)).isTrue();

    // Verify the correct message was printed
    String expectedMessage = "Die Datei und das Spiel " + gameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }
}
