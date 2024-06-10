package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import hwr.oop.doppelkopf.group6.cli.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTest {
  CreateCommand command;
  ParseCommand parse = new ParseCommand();
  String fileName = "doppelkopf.csv";
  Path currentRelativePath = Paths.get("");
  String currentDir = currentRelativePath.toAbsolutePath().toString();
  File file = new File(currentDir + File.separator + fileName);
  Path path = file.toPath();

  @BeforeEach
  void setUp() throws IOException {
    command = new CreateCommand(IOExceptionBomb.DONT);

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
  void testCreateGameWithEmptyLineInFile() throws IOException {
    // Setup an existing file with an empty line
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.newLine(); // Write an empty line
    }

    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("2");
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    command.execute(args);

    // Check the output to ensure the game creation proceeds
    String output = outputStream.toString().trim();
    assertThat(output).contains("Spiel 2 wird erstellt...");
  }

  @Test
  void testCreateGameWithFileNotExisting() throws IOException {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    String gameID = parse.gameID(args);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    command.execute(args);

    assertThat(Files.exists(path)).isTrue();

    String expectedMessage = "Spiel " + gameID + " wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWithGameExisting() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add(null);
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setErr(printStream);

    command.execute(args);

    String expectedMessage =
        "Game ID: " + "\"" + args.get(1) + "\"" + " is not a valid game ID. Please use numbers!";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWithFileExisting() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    command.execute(args);

    assertThat(Files.exists(path)).isTrue();

    String expectedMessage = "Die Datei wird erstellt...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWhenGameAlreadyExistsIOException() throws IOException {
    PlayCommand play = new PlayCommand(IOExceptionBomb.DO, new Deck());
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    String gameID = parse.gameID(args);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write("2,maria,cards: ,hans,cards: ,fritz,cards: ,lisa,cards: ");
      writer.newLine();
      writer.write(gameID);
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    play.execute(args);

    String expectedMessage = "Caught an IOException: Here goes everything exploding...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testIOException() {
    command = new CreateCommand(IOExceptionBomb.DO);
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setErr(printStream);

    command.execute(args);

    String expectedMessage = "IOException aufgetreten: Here goes everything exploding...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testIOExceptionWhenFileExists() throws IOException {
    String gameID = "12";
    command = new CreateCommand(IOExceptionBomb.DO);
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(gameID);
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setErr(printStream);

    command.execute(args);

    String expectedMessage = "IOException aufgetreten: Here goes everything exploding...";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testCreateGameWhenGameAlreadyExists() throws IOException {
    CreateCommand create = new CreateCommand(IOExceptionBomb.DONT);
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write("1,josef,cards: ,anna,cards: ,jannis,cards: ,lena,cards: ");
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setErr(printStream);

    create.execute(args);

    String expectedMessage =
        "IOException aufgetreten: Das Spiel existiert bereits! Probiere eine andere Spiel ID.";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testSavePlayersToFile() throws IOException {
    CreateCommand create = new CreateCommand(IOExceptionBomb.DONT);
    List<String> args = List.of("game", "2", "create", "josef", "anna", "jannis", "lena");

    create.execute(args);

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      boolean gameIDFound = false;
      while ((line = reader.readLine()) != null) {
        if (line.contains("2,josef,cards: ")
            && line.contains("anna,cards: ")
            && line.contains("jannis,cards: ")
            && line.contains("lena,cards: ")) {
          gameIDFound = true;
          break;
        }
      }
      assertThat(gameIDFound).isTrue();
    }
  }
}
