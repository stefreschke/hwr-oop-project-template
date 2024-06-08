package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import hwr.oop.doppelkopf.group6.cli.CreateCommand;
import hwr.oop.doppelkopf.group6.cli.IOExceptionBomb;
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
  void testCreateGameWithFileNotExisting() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    String gameID = command.parseGameID(args);
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
  void testCreateGameWhenGameAlreadyExists() throws IOException {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    args.add("josef");
    args.add("anna");
    args.add("jannis");
    args.add("lena");

    String gameID = command.parseGameID(args);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write("2,maria,cards: ,hans,cards: ,fritz,cards: ,lisa,cards: ");
      writer.newLine();
      writer.write(gameID);
      writer.newLine();
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    command.execute(args);

    String expectedMessage = "Das Spiel existiert bereits, wähle eine andere ID für das Spiel!";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }


  @Test
  void testCreateGameWithNoWords() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("       ");
    args.add("create");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    command.execute(args);
    String result = command.parseGameID(args);

    String expectedMessage = "Game ID: " + "\"" + args.get(1) + "\"" + " is not a valid game ID. Please use numbers!";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
    assertThat(result).isNull();
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
}
