package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;
import hwr.oop.doppelkopf.group6.DoppelkopfGame;
import hwr.oop.doppelkopf.group6.Player;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class PlayCommand implements Command {
  private final IOExceptionBomb ioExceptionBomb;
  public final ParseCommand parse;
  DoppelkopfGame game = new DoppelkopfGame();
  private final Deck deck;
  private final PrintStream out;

  public PlayCommand(Deck deck, OutputStream out, IOExceptionBomb ioExceptionBomb) {
    this.ioExceptionBomb = ioExceptionBomb;
    this.deck = deck;
    this.out = new PrintStream(out);
    this.parse = new ParseCommand(out);
  }

  public PlayCommand(Deck deck, OutputStream out, IOExceptionBomb ioExceptionBomb, ParseCommand parseCommand) {
    this.ioExceptionBomb = ioExceptionBomb;
    this.deck = deck;
    this.out = new PrintStream(out);
    this.parse = parseCommand;
  }

  @Override
  public void execute(List<String> args) {
    try {
      String gameID = parse.gameID(args);
      ioExceptionBomb.fire();
      List<Player> currentPlayers = parse.players(args);
      deck.initializeCards();
      deck.shuffleDeck();
      deck.dealCards(currentPlayers);
      game.checkForPoverty(currentPlayers);
      if(gameID!= null){
        out.println("Starting the game with game ID " + gameID);
      }
    } catch (Exception e) {
      out.println("Caught an IOException: " + e.getMessage());
    }
  }
}
