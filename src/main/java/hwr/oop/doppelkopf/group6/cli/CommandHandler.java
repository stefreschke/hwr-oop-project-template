package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;
import java.util.*;

public class CommandHandler {
  public final Map<String, Command> commands = new HashMap<>();

  public CommandHandler() {
    commands.put("create", new CreateCommand(IOExceptionBomb.DONT));
    commands.put("play", new PlayCommand(IOExceptionBomb.DONT, new Deck()));
    // weitere Commands
  }

  public void parse(List<String> args) {
    if (args.isEmpty()) {
      System.out.println("Kein Command wurde übergeben!");
      return;
    }

    // command format:
    //   0   1      2         ...
    // game [ID] [create/usw.]...
    String commandKey = args.get(2);
    Command command = commands.get(commandKey);
    command.execute(args);
  }
}
