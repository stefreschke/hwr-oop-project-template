package hwr.oop.doppelkopf.group6;

import hwr.oop.doppelkopf.group6.cli.ParseCommand;

@SuppressWarnings("java:S106")
public class StartGame {

  public static void main(String[] args) {
    ParseCommand command = new ParseCommand();
    command.parse(args);
  }
}
