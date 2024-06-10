package hwr.oop.doppelkopf.group6;

import hwr.oop.doppelkopf.group6.cli.ParseCommand;

@SuppressWarnings("java:S106")
public class StartGame {

  private static ParseCommand parseCommand = new ParseCommand();

  public static void setParseCommand(ParseCommand parseCommand) {
    StartGame.parseCommand = parseCommand;
  }

  public static void main(String[] args) {
    parseCommand.parse(args);
  }
}
