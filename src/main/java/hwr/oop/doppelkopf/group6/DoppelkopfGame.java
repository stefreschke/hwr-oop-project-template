package hwr.oop.doppelkopf.group6;

import java.util.ArrayList;
import java.util.List;

public class DoppelkopfGame {
    public final Player player1 = new Player("Spieler1", 1);
    public final Player player2 = new Player("Spieler2", 2);
    public final Player player3 = new Player("Spieler3", 3);
    public final Player player4 = new Player("Spieler4", 4);

    public DoppelkopfGame() {
        initializeCards();
    }


    public List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();

        for (int k = 0; k < 2; k++) {
            for (Color i : Color.values()) {
                for (Type j : Type.values()) {
                    Card newCard = new Card(i, j);
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
}
