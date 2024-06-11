package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import hwr.oop.doppelkopf.group6.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandTest {
  private OutputStream outputStream;
  ParseCommand parse;
  CommandHandler cmd;

  @BeforeEach
  void setUp() {
    this.outputStream = new ByteArrayOutputStream();
    this.parse = new ParseCommand(outputStream);
    this.cmd = new CommandHandler(outputStream);
  }

  @Test
  void testParseID() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");

    String gameID = parse.gameID(args);

    assertThat(gameID).isEqualTo(args.get(1));
  }

  @Test
  void testParseIdReturnsNull() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("");
    args.add("create");

    String gameID = parse.gameID(args);

    assertThat(gameID).isNull();
  }

  @Test
  void testParseCommandWithNoArgs() {
    List<String> args = new ArrayList<>();

    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    cmd.parse(args);

    System.setOut(System.out);
    String expectedMessage = "Kein Command wurde übergeben!";
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testParseCommandExecutesCreateCommand() {
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    CommandHandler parseCommand = new CommandHandler(System.out);

    Command createCommandMock = mock(CreateCommand.class);
    parseCommand.commands.put("create", createCommandMock);

    parseCommand.parse(args);

    List<String> expectedArguments = Arrays.asList("game", "1", "create");
    verify(createCommandMock).execute(expectedArguments);
  }

  @Test
  void testParsePlayer() throws IOException {
    List<Player> players;
    List<String> args = new ArrayList<>();
    args.add("game");
    args.add("1");
    args.add("create");
    args.add("susi");
    args.add("rainer");
    args.add("brigitte");
    args.add("joachim");

    players = parse.players(args);

    assertThat(players.getFirst().getName()).isEqualTo("susi");
  }

  @Test
  void testPlayCommand_initCards() {
    PlayCommand play = new PlayCommand(new Deck(), outputStream, IOExceptionBomb.DONT);
    List<String> args = List.of("game", "1", "create", "susi", "rainer", "brigitte", "joachim");

    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    play.execute(args);

    String expectedMessage = "Starting the game with game ID " + parse.gameID(args);
    String output = outputStream.toString().trim();
    assertThat(output).contains(expectedMessage);
  }

  @Test
  void testShuffleDeckIsCalled() {
    Deck deckMock = mock(Deck.class);
    PlayCommand playCommand = new PlayCommand(deckMock, outputStream, IOExceptionBomb.DONT);
    List<String> args = List.of("game", "1", "create", "susi", "rainer", "brigitte", "joachim");

    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    playCommand.execute(args);

    verify(deckMock).shuffleDeck();
  }

  @Test
  void testDealCardsIsCalled() throws Exception {
    Deck deckMock = mock(Deck.class);
    List<String> args = List.of("game", "1", "create", "susi", "rainer", "brigitte", "joachim");

    List<Player> players =
            List.of(
                    new Player("susi", 1, 0),
                    new Player("rainer", 2, 0),
                    new Player("brigitte", 3, 0),
                    new Player("joachim", 4, 0));

    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    ParseCommand parseCommandMock = mock(ParseCommand.class);
    when(parseCommandMock.players(args)).thenReturn(players);
    PlayCommand playCommand = new PlayCommand(deckMock, System.out, IOExceptionBomb.DONT, parseCommandMock);

    playCommand.execute(args);

    verify(deckMock).dealCards(players);
  }
}