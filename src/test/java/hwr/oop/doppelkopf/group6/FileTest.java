package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTest {
  StartGame startGame;
  String filePath = "/Users/lukaskarsten/Desktop/test.txt";
  File file = new File(filePath);
  Path path = file.toPath();

  @BeforeEach
  void setUp() throws IOException {
    startGame = new StartGame(IOExceptionBomb.DONT);
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

  @Test
  void testCreateGameWhenGameAlreadyExists() throws IOException {
    String gameID = "1";
    String[] args = {"create", gameID};

    // Create the file and write the gameID into it
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(gameID);
      writer.newLine();
    }

    // Capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);

    // Verify the correct message was printed
    String expectedMessage = "Das Spiel existiert bereits, wähle eine andere ID für das Spiel!";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWithNewID() throws IOException {
    String existingGameID = "1";
    String newGameID = "2";
    String[] args = {"create", newGameID};

    // Create the file and write the existing gameID into it
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(existingGameID);
      writer.newLine();
    }

    // Capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);

    // Verify the file was updated with the new gameID
    assertThat(Files.exists(path)).isTrue();

    // Verify the correct message was printed
    String expectedMessage = "Spiel " + newGameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);

    // Verify that both game IDs exist in the file
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      assertThat(reader.lines()).contains(existingGameID, newGameID);
    }
  }

}
