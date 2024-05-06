package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class DoppelkopfGame {
  public final Player player1 = new Player("Spieler1", 1, 0);
  public final Player player2 = new Player("Spieler2", 2, 0);
  public final Player player3 = new Player("Spieler3", 3, 0);
  public final Player player4 = new Player("Spieler4", 4, 0);

  public DoppelkopfGame() {
    initializeCards();
  }

  public int oneRound() {
    List<Card> roundCards = new ArrayList<>();
    roundCards.add(player1.showAndChooseCard());
    roundCards.add(player2.showAndChooseCard());
    roundCards.add(player3.showAndChooseCard());
    roundCards.add(player4.showAndChooseCard());
    return findHighestCard(roundCards);
  }

  public List<Card> initializeCards() {
    List<Card> cards = new ArrayList<>();

    for (int k = 0; k < 2; k++) {
      for (Color i : Color.values()) {
        for (Type j : Type.values()) {
            Card newCard;
            if (i == Color.KARO || j == Type.BUBE || j == Type.DAME || (i == Color.HERZ && j == Type.ZEHN)){
                newCard = new Card(i, j, true, i.getShortcut() + j.getShortcut());
            }else{
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
      player1.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
      player2.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
      player3.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
      player4.getOwnCards().add(cards.getFirst());
      cards.remove(cards.getFirst());
    }
  }

  public int findHighestCard( List<Card> cards) {
    Card highestCard = cards.getFirst();
    Card firstCard = highestCard;
    int winner = 0;
    cards.removeFirst();
    for (int i = 0; i < cards.size(); i++){
      if ((cards.get(i).isTrump()) || (cards.get(i).getColor() == highestCard.getColor() && cards.get(i).getNumber().getStrength() > highestCard.getNumber().getStrength())){
        highestCard = cards.get(i);
        winner = i+1;
      }
    }
    cards.add(firstCard);

    switch (winner) {
      case 0:
        player1.addPoints(cards);
        break;
      case 1:
        player2.addPoints(cards);
        break;
      case 2:
        player3.addPoints(cards);
        break;
      case 3:
        player4.addPoints(cards);
        break;
      default:
        break;
    }
    return winner;
  }


  List<String> TrumpCards = new ArrayList<>();
  public List<String> getTrumpCards() {
    return TrumpCards;
  }

  List<String> HerzCards = new ArrayList<>();
  public List<String> getHerzCards() {
    return HerzCards;
  }

  List<String> PikCards = new ArrayList<>();
  public List<String> getPikCards() {
    return PikCards;
  }

  List<String> KreuzCards = new ArrayList<>();
  public List<String> getKreuzCards() {
    return KreuzCards;
  }


  public void SortCards(List<Card> cards, String player) {
    for (Card i : player1.getOwnCards()) {
      if (i.isTrump()){
        TrumpCards.add(i.getShortcut());
      }else if(i.getColor() == Color.HERZ){
        HerzCards.add(i.getShortcut());
      }else if(i.getColor() == Color.PIK){
        PikCards.add(i.getShortcut());
      }else if(i.getColor() == Color.KREUZ){
        KreuzCards.add(i.getShortcut());
      }
    }
  }




}
