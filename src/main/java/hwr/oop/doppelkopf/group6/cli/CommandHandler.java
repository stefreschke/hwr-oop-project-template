package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class CommandHandler {
  public final Map<String, Command> commands = new HashMap<>();
  private final PrintStream out;

  public CommandHandler(OutputStream outputStream) {
    this.out = new PrintStream(outputStream);
    commands.put("create", new CreateCommand(outputStream, IOExceptionBomb.DONT));
    commands.put("play", new PlayCommand(new Deck(), outputStream, IOExceptionBomb.DONT));
    // weitere Commands
  }

  public void parse(List<String> args) {
    if (args.isEmpty()) {
      out.println("Kein Command wurde übergeben!");
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