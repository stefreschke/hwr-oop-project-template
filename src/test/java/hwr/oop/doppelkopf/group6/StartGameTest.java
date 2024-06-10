package hwr.oop.doppelkopf.group6;

import hwr.oop.doppelkopf.group6.cli.ParseCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class StartGameTest {

  private ParseCommand mockParseCommand;

  @BeforeEach
  void setUp() {
    mockParseCommand = mock(ParseCommand.class);
    StartGame.setParseCommand(mockParseCommand);
  }

  @Test
  void testMainMethodCallsParseCommand() {
    String[] args = {"argument1", "argument2"};

    StartGame.main(args);

    verify(mockParseCommand).parse(args);
  }
}
