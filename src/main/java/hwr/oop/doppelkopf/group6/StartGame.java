package hwr.oop.doppelkopf.group6;

import hwr.oop.doppelkopf.group6.cli.CommandHandler;
import java.util.Arrays;

public class StartGame {

  @SuppressWarnings("java:S106")
  public static void main(String[] args) {
    CommandHandler command = new CommandHandler(System.out);
    command.parse(Arrays.asList(args));
  }
}