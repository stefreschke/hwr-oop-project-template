package hwr.oop.doppelkopf.group6;

import hwr.oop.doppelkopf.group6.cli.CommandHandler;
import java.util.Arrays;

public class StartGame {

  public static void main(String[] args) {
    CommandHandler command = new CommandHandler();
    command.parse(Arrays.asList(args));
  }
}
