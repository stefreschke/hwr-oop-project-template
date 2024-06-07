package hwr.oop.doppelkopf.group6;

import java.util.*;

public class DoppelkopfGame {
  public final List<Player> players = new ArrayList<>();

  public DoppelkopfGame() {
    initializePlayers();
    dealCards(initializeCards());
    oneGame();
  }

  public void oneGame(){
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
          } else {
            player.setGroup("Kontra");
          }
        }
        players.get(winner).setGroup("Re");
        hochzeit = false;
      }
    }
  }

  public List<Card> shuffleDeck(List<Card> cards) {
    final var mutableList = new ArrayList<>(cards);
    Collections.shuffle(mutableList);
    return mutableList;
  }

  public void initializePlayers() {
    players.add(new Player("Spieler1", 1, 0));
    players.add(new Player("Spieler2", 2, 0));
    players.add(new Player("Spieler3", 3, 0));
    players.add(new Player("Spieler4", 4, 0));
  }

  public int oneRound() {
    List<Card> roundCards = new ArrayList<>();
    roundCards.add(players.get(0).playFirstCard(0));
    if (roundCards.getFirst().isTrump()) {
      roundCards.add(players.get(1).playCard(0));
      roundCards.add(players.get(2).playCard(0));
      roundCards.add(players.get(3).playCard(0));
    } else {
      roundCards.add(players.get(1).playCard(0, roundCards.getFirst().getColor()));
      roundCards.add(players.get(2).playCard(0, roundCards.getFirst().getColor()));
      roundCards.add(players.get(3).playCard(0, roundCards.getFirst().getColor()));
    }
    return findHighestCard(roundCards);
  }

  public void addRoundPoints(List<Card> roundCards, int winner){
    for (Player player : players) {
      if (Objects.equals(
              player.getGroup(), players.get(winner).getGroup())) {
        player.addPoints(roundCards);
      }
    }
  }

  public List<Card> initializeCards() {
    List<Card> cards = new ArrayList<>();
    for (int k = 0; k < 2; k++) {
      for (Color i : Color.values()) {
        for (Type j : Type.values()) {
          Card newCard;
          if (i == Color.KARO
              || j == Type.BUBE
              || j == Type.DAME
              || (i == Color.HERZ && j == Type.ZEHN)) {
            newCard = new Card(i, j, true, i.getShortcut() + j.getShortcut());
          } else {
            newCard = new Card(i, j, false, i.getShortcut() + j.getShortcut());
          }
          cards.add(newCard);
        }
      }
    }
    return cards;
  }

  public boolean hasCard(List<Card> cards, Color color, Type number) {
    for (Card i : cards) {
      if (i.getColor() == color && i.getNumber() == number) {
        return true;
      }
    }
    return false;
  }

  public void dealCards(List<Card> cards) {
    for (int i = 0; i < 12; i++) {
      players.get(0).addCard(cards.getFirst());
      cards.remove(cards.getFirst());
      players.get(1).addCard(cards.getFirst());
      cards.remove(cards.getFirst());
      players.get(2).addCard(cards.getFirst());
      cards.remove(cards.getFirst());
      players.get(3).addCard(cards.getFirst());
      cards.remove(cards.getFirst());
    }
  }

  public int findHighestCard(List<Card> cards) {
    Card highestCard = cards.getFirst();
    Card firstCard = highestCard;
    int winnerNumber = 0;
    cards.removeFirst();
    for (int i = 0; i < cards.size(); i++) {
      if (((cards.get(i).isTrump()) || cards.get(i).getColor() == highestCard.getColor())
              && (cards.get(i).getNumber().getStrength() > highestCard.getNumber().getStrength())
          || Objects.equals(cards.get(i).getShortcut(), "H10")) {
        highestCard = cards.get(i);
        winnerNumber = i + 1;
      }
    }
    cards.add(firstCard);
    addRoundPoints(cards, winnerNumber);
    return winnerNumber + 1;
  }

  private final List<String> trumpCards = new ArrayList<>();

  public List<String> getTrumpCards() {
    return new ArrayList<>(trumpCards);
  }

  private final List<String> herzCards = new ArrayList<>();

  public List<String> getHerzCards() {
    return new ArrayList<>(herzCards);
  }

  private final List<String> pikCards = new ArrayList<>();

  public List<String> getPikCards() {
    return new ArrayList<>(pikCards);
  }

  private final List<String> kreuzCards = new ArrayList<>();

  public List<String> getKreuzCards() {
    return new ArrayList<>(kreuzCards);
  }

  public void sortCards(String playerName) {
    Player player = null;
    for (Player p : players) {
      if (p.getName().equals(playerName)) {
        player = p;
        break;
      }
    }
    if (player == null) {
      throw new IllegalArgumentException("Player not found");
    }

    for (Card i : player.getOwnCards()) {
      if (i.isTrump()) {
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
}
