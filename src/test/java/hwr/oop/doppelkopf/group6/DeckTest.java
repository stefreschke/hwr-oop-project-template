package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeckTest {
  @Test
  void testSortCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.players.getFirst().getHand().getTrumpCards().add("K10");
    game.players.getFirst().getHand().getHerzCards().add("H10");
    game.players.getFirst().getHand().getPikCards().add("P10");
    game.players.getFirst().getHand().getKreuzCards().add("K10");

    // Rufe die sortCards Methode auf
    game.players.getFirst().getHand().sortCards();

    Card kreuzCard = new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"); // Trumpfkarte erstellen
    game.players.getFirst().getOwnCards().add(kreuzCard);

    Card herzCard = new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"); // Trumpfkarte erstellen
    game.players.getFirst().getOwnCards().add(herzCard);

    Card pikCard = new Card(Color.PIK, Type.NEUN, Group.PIK, "P9");
    game.players.getFirst().getOwnCards().add(pikCard);

    Card trumpCard = new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD");
    game.players.getFirst().getOwnCards().add(trumpCard);
    // Karte dem Spieler hinzufügen

    game.players.getFirst().getHand().sortCards(); // Sortiere die Karten des Spielers

    assertTrue(
        game.players.getFirst().getHand().getKreuzCards().contains(kreuzCard.getShortcut()),
        "Kreuz card should be added to KreuzCards");
  }

  @Test
  void testCreateDeck() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.createDeck();

    DoppelkopfGame game2 = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.dealCards(game2.players);

    assertThat(game.deck.getShuffled()).isNotEqualTo(deck.getShuffled());
  }
}
