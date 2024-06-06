package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import hwr.oop.doppelkopf.group6.cli.Command;
import hwr.oop.doppelkopf.group6.cli.CreateCommand;
import hwr.oop.doppelkopf.group6.cli.IOExceptionBomb;
import hwr.oop.doppelkopf.group6.cli.ParseCommand;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CommandTest {
  CreateCommand command = new CreateCommand(IOExceptionBomb.DONT);
  ParseCommand cmd = new ParseCommand();


  @Test
  void testParseID() {
      List<String> args = new ArrayList<>();
      args.add("game");
      args.add("1");
      args.add("create");

      String gameID = command.parseGameID(args);

      assertThat(gameID).isEqualTo(args.get(1));
  }

  @Test
  void testParseCommandWithNoArgs() {
      String[] args = new String[0];

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      PrintStream printStream = new PrintStream(outputStream);
      System.setOut(printStream);

      cmd.parse(args);

      System.setOut(System.out);
      String expectedMessage = "Keine Argumente übergeben. Nutzung: ./doppelkopf <Befehl>";
      String output = outputStream.toString().trim();
      assertThat(output).contains(expectedMessage);
  }


  // weiß nicht, ob wir "Mockito" benutzen dürfen. Ist zum überprüfen ob Funktionen executed wurden.
    @Test
    void testParseCommandExecutesCreateCommand() {
        String[] args = {"game", "create", "1"};
        ParseCommand parseCommand = new ParseCommand();

        Command createCommandMock = mock(CreateCommand.class);
        parseCommand.commands.put("create", createCommandMock);

        parseCommand.parse(args);

        List<String> expectedArguments = Arrays.asList("create", "1");
        verify(createCommandMock).execute(expectedArguments);
    }
}
