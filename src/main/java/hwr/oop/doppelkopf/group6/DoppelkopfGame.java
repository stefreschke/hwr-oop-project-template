package hwr.oop.doppelkopf.group6;

import java.util.*;

@SuppressWarnings("java:S106")
public class DoppelkopfGame {
  public final List<Player> players = new ArrayList<>();
  public final Deck deck = new Deck();

  public DoppelkopfGame() {
    initializePlayers();
  }

  public void createDeck(){
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(players);
  }

  public void oneGame() {
        boolean hochzeit = false;
    int playerWithHochzeit = 0;
    for (Player player : players) {
      player.setGroup();
      if (player.getGroup().equals("Hochzeit")) {
        hochzeit = true;
        playerWithHochzeit = player.getOrder() - 1;
      }
    }
    for (int i = 0; i < 12; i++) {
      int winner = oneRound() - 1;
      if ((hochzeit) && (winner != playerWithHochzeit)) {
        for (Player player : players) {
          if (player.getGroup().equals("Hochzeit")) {
            player.setGroup("Re");
          }
        }
        players.get(winner).setGroup("Re");
        hochzeit = false;
      }
    }
  }

  public void initializePlayers() {
    players.add(new Player("Spieler1", 1, 0));
    players.add(new Player("Spieler2", 2, 0));
    players.add(new Player("Spieler3", 3, 0));
    players.add(new Player("Spieler4", 4, 0));
  }

  public int oneRound() {
    Stich stich = new Stich();
    players.get(0).playFirstCard(0, stich);
    players.get(1).playCard(0, stich);
    players.get(2).playCard(0, stich);
    players.get(3).playCard(0, stich);
    int winner = stich.findHighestCard();
    addRoundPoints(stich);
    return winner;
  }

  public void addRoundPoints(Stich stich) {
    String winnerTeam = players.get(stich.getWinnerPos()).getGroup();
    for (Player player : players) {
      if (player.getGroup().equals(winnerTeam)) {
        player.addPoints(stich.getPoints());
      }
    }
  }

  private List<String> trumpCards = new ArrayList<>();

  public List<String> getTrumpCards() {
    return new ArrayList<>(trumpCards);
  }

  private List<String> herzCards = new ArrayList<>();

  public List<String> getHerzCards() {
    return new ArrayList<>(herzCards);
  }

  private List<String> pikCards = new ArrayList<>();

  public List<String> getPikCards() {
    return new ArrayList<>(kreuzCards);
  }

  private List<String> kreuzCards = new ArrayList<>();

  public List<String> getKreuzCards() {
    return new ArrayList<>(kreuzCards);
  }

  public void sortCards(int playerIndex) {
    trumpCards = new ArrayList<>();
    herzCards = new ArrayList<>();
    pikCards = new ArrayList<>();
    kreuzCards = new ArrayList<>();

    Player player = players.get(playerIndex);

    for (Card i : player.getOwnCards()) {
      if (i.getGroup().equals(Group.TRUMPF)) {
        trumpCards.add(i.getShortcut());
      } else if (i.getColor() == Color.HERZ) {
        herzCards.add(i.getShortcut());
      } else if (i.getColor() == Color.PIK) {
        pikCards.add(i.getShortcut());
      } else if (i.getColor() == Color.KREUZ) {
        kreuzCards.add(i.getShortcut());
      }
    }
  }

  public void switchPlayerCardsDuringPoverty(
      List<Card> poorPlayerCards,
      int poorPlayerIndex,
      List<Card> richPlayerCards,
      int richPlayerIndex) {
    for (Card i : poorPlayerCards) {
      players.get(poorPlayerIndex).getOwnCards().removeIf(i::equals);
      players.get(richPlayerIndex).getOwnCards().add(i);
    }

    for (Card i : richPlayerCards) {
      players.get(richPlayerIndex).getOwnCards().removeIf(i::equals);
      players.get(poorPlayerIndex).getOwnCards().add(i);
    }
  }

  public boolean checkForPoverty(List<Player> player) {
    for (Player i : player) {
      if (i.countPlayersTrumpCards() <= 3) {
        return true;
      }
    }
    return false;
  }
}
