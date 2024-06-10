package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;
import hwr.oop.doppelkopf.group6.DoppelkopfGame;
import hwr.oop.doppelkopf.group6.Player;
import java.util.List;

public class PlayCommand implements Command {
  private final IOExceptionBomb ioExceptionBomb;
  public ParseCommand parse = new ParseCommand();
  DoppelkopfGame game = new DoppelkopfGame();
  private final Deck deck;

  public PlayCommand(IOExceptionBomb ioExceptionBomb, Deck deck) {
    this.ioExceptionBomb = ioExceptionBomb;
    this.deck = deck;
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
      System.out.println("Starting the game with game ID " + gameID);
    } catch (Exception e) {
      System.out.println("Caught an IOException: " + e.getMessage());
    }
  }
}
