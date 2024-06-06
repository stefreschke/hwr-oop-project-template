package hwr.oop.doppelkopf.group6.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S106")
public class ParseCommand {
  public final Map<String, Command> commands = new HashMap<>();
  String gameID;

  public ParseCommand() {
    commands.put("create", new CreateCommand(IOExceptionBomb.DONT));
    // weitere Commands
  }

  public void parse(String[] args) {
    if (args.length == 0) {
      System.out.println("Keine Argumente übergeben. Nutzung: ./doppelkopf <Befehl>");
      return;
    }

    // command format:
    //   0   1      2         ...
    // game [ID] [create/usw.]...
    String commandKey = args[1];
    Command command = commands.get(commandKey);

    List<String> arguments = Arrays.asList(args).subList(1, args.length);
    command.execute(arguments);
  }
}
