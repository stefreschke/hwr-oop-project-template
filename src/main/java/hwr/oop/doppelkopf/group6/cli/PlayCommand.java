package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;
import hwr.oop.doppelkopf.group6.DoppelkopfGame;
import hwr.oop.doppelkopf.group6.Player;
import hwr.oop.doppelkopf.group6.persistenz.SaveToFile;
import java.util.List;

public class PlayCommand implements Command {
  private final IOExceptionBomb ioExceptionBomb;
  ParseCommand parse;
  DoppelkopfGame game = new DoppelkopfGame();
  private final SaveToFile save;
  private final Deck deck;

  public PlayCommand(IOExceptionBomb ioExceptionBomb, Deck deck, SaveToFile save, ParseCommand parse) {
    this.ioExceptionBomb = ioExceptionBomb;
    this.deck = deck;
    this.save = save;
    this.parse = parse;
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
      save.cards(currentPlayers, gameID);
    } catch (Exception e) {
      System.out.println("Caught an IOException: " + e.getMessage());
    }
  }
}
