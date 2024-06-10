package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseCommand {
  public List<Player> players(List<String> args) throws IOException {
    String regularExpression = "[a-zA-Z]+";
    if (args.size() != 7
        || (args.get(3).isBlank() || !args.get(3).matches(regularExpression))
        || (args.get(4).isBlank() || !args.get(4).matches(regularExpression))
        || (args.get(5).isBlank() || !args.get(5).matches(regularExpression))
        || (args.get(6).isBlank() || !args.get(6).matches(regularExpression))) {
      throw new IOException("Something went wrong regarding the Players.");
    }
    List<Player> playerList = new ArrayList<>();
    playerList.add(new Player(args.get(3), 1, 0));
    playerList.add(new Player(args.get(4), 2, 0));
    playerList.add(new Player(args.get(5), 3, 0));
    playerList.add(new Player(args.get(6), 4, 0));

    return playerList;
  }

  public String gameID(List<String> args) throws IOException {
    if (args.get(1) == null || args.get(1).isBlank() || !args.get(1).matches("\\d+")) {
      throw new IOException(
          "Game ID: " + "\"" + args.get(1) + "\"" + " is not a valid game ID. Please use numbers!");
    }
    return args.get(1);
  }
}
