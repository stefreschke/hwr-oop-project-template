package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTest {
  StartGame startGame;
  String fileName = "doppelkopf.txt";
  Path currentRelativePath = Paths.get("");
  String currentDir = currentRelativePath.toAbsolutePath().toString();
  File file = new File(currentDir + File.separator + fileName);
  Path path = file.toPath();

  @BeforeEach
  void setUp() throws IOException {
    startGame = new StartGame(IOExceptionBomb.DONT);

    if (Files.exists(path)) {
      Files.delete(path);
    }
  }

  @AfterEach
  void tearDown() throws IOException {

    if (Files.exists(path)) {
      Files.delete(path);
    }
  }

  @Test
  void testCreateGameWithFileNotExisting() {
    String gameID = "1";
    String[] args = {"create", gameID};


    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);


    assertThat(Files.exists(path)).isTrue();


    String expectedMessage = "Die Datei und das Spiel " + gameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWhenGameAlreadyExists() throws IOException {
    String gameID = "1";
    String[] args = {"create", gameID};

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(gameID);
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);

    String expectedMessage = "Das Spiel existiert bereits, wähle eine andere ID für das Spiel!";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWithNewID() throws IOException {
    String existingGameID = "1";
    String newGameID = "2";
    String[] args = {"create", newGameID};

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(existingGameID);
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);

    assertThat(Files.exists(path)).isTrue();

    String expectedMessage = "Spiel " + newGameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      assertThat(reader.lines()).contains(existingGameID, newGameID);
    }
  }

  @Test
  void testCreateGameWithEmptyLine() throws IOException {
    String gameID = "1";
    String[] args = {"create", gameID};


    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);

    String expectedMessage = "Spiel " + gameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);


    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      assertThat(reader.lines()).contains(gameID);
    }
  }

  @Test
  void testCreateGameWithNoWords() throws IOException {
    String gameID = "1";
    String[] args = {"create", gameID};


    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write("   ");  // Write a line with only spaces
      writer.newLine();
    }


    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    StartGame.main(args);


    String expectedMessage = "Spiel " + gameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);


    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      assertThat(reader.lines()).contains(gameID);
    }
  }

  @Test
  void testIOException() {
    startGame = new StartGame(IOExceptionBomb.DO);
    String gameID = "1";


    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setErr(printStream);

    startGame.createGame(gameID);


    String expectedMessage = "IOException aufgetreten: Here goes everything exploding...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testIOExceptionWhenFileExists() throws IOException {
    startGame = new StartGame(IOExceptionBomb.DO);
    String gameID = "1";


    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(gameID);
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setErr(printStream);

    startGame.createGame(gameID);

    String expectedMessage = "IOException aufgetreten: Here goes everything exploding...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }
}
