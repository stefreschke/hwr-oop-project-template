package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;
import hwr.oop.doppelkopf.group6.DoppelkopfGame;
import hwr.oop.doppelkopf.group6.Player;

import java.io.OutputStream;
import java.io.PrintStream;
import hwr.oop.doppelkopf.group6.persistence.SaveToFile;
import java.util.List;

public class InitCommand implements Command {
  private final IOExceptionBomb ioExceptionBomb;
  public final ParseCommand parse;
  DoppelkopfGame game = new DoppelkopfGame();
  private final SaveToFile save;
  private final Deck deck;
  private final PrintStream out;

  public InitCommand(IOExceptionBomb ioExceptionBomb, OutputStream out, Deck deck, SaveToFile save, ParseCommand parse) {
    this.ioExceptionBomb = ioExceptionBomb;
    this.deck = deck;
    this.out = new PrintStream(out);
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
      out.println("Starting the game with game ID " + gameID);
      save.cards(currentPlayers, gameID);
    } catch (Exception e) {
      out.println("Caught an IOException: " + e.getMessage());
    }
  }
}
