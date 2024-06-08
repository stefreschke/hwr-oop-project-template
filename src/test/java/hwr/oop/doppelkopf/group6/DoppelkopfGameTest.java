package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class DoppelkopfGameTest {
  @Test
  void testOneRound() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
      for (Player player : game.players) {
          player.setGroup();
      }
      int winner = game.oneRound();
    assertThat(winner).isBetween(1, 4);
  }

  @Test
  void testOneRoundWithTrump() {
    DoppelkopfGame game = new DoppelkopfGame();
    Stich stich1 = new Stich();
    Stich stich2 = new Stich();
    stich1.addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
    stich2.addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    game.players.get(0).addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
    game.players.get(0).addCard(new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"));
    game.players.get(1).addCard(new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));
    game.players.get(1).addCard(new Card(Color.PIK, Type.ASS, Group.PIK, "PA"));
    game.players.get(2).addCard(new Card(Color.KARO, Type.KOENIG, Group.TRUMPF, "KK"));
    game.players.get(2).addCard(new Card(Color.KARO, Type.BUBE, Group.TRUMPF, "KB"));
    game.players.get(3).addCard(new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    game.players.get(3).addCard(new Card(Color.KARO, Type.DAME, Group.TRUMPF, "KD"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly
              .assertThat(
                  stich1.checkCard(
                      game.players.getFirst().getOwnCards(),
                      game.players.getFirst().getOwnCards().get(1)))
              .isFalse();
          softly
              .assertThat(
                  stich1.checkCard(
                      (game.players.getFirst().getOwnCards()),
                      game.players.getFirst().getOwnCards().getFirst()))
              .isTrue();
          softly
              .assertThat(
                  stich2.checkCard(
                      (game.players.getFirst().getOwnCards()),
                      game.players.getFirst().getOwnCards().get(1)))
              .isTrue();
          softly
              .assertThat(
                  stich2.checkCard(
                      (game.players.getFirst().getOwnCards()),
                      game.players.getFirst().getOwnCards().getFirst()))
              .isFalse();
        });
  }

  @Test
  void testFindHighestCard() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
    game.players.get(0).setGroup("Re");
    game.players.get(1).setGroup("Re");
    game.players.get(2).setGroup("Kontra");
    game.players.get(3).setGroup("Kontra");

    for (Player player : game.players) {
      player.resetPoints();
    }

    Stich stich1 = new Stich();
    Stich stich2 = new Stich();
    Stich stich3 = new Stich();
    Stich stich4 = new Stich();
    Stich stich5 = new Stich();
    Stich stich6 = new Stich();
    Stich stich7 = new Stich();
    Stich stich8 = new Stich();

    stich1.addCard(
        new Card(Color.HERZ, Type.ASS, Group.HERZ, "HA"),
        new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"),
        new Card(Color.PIK, Type.ASS, Group.PIK, "PA"));
    stich2.addCard(
        new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"),
        new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"));
    stich3.addCard(
        new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"),
        new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"),
        new Card(Color.PIK, Type.ASS, Group.PIK, "PA"),
        new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));
    stich4.addCard(
        new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"),
        new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"),
        new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"),
        new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"));
    stich5.addCard(
        new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"),
        new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"),
        new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"),
        new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    stich6.addCard(
        new Card(Color.HERZ, Type.ASS, Group.HERZ, "HA"),
        new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"),
        new Card(Color.HERZ, Type.ASS, Group.HERZ, "HA"));
    stich7.addCard(
        new Card(Color.HERZ, Type.ZEHN, Group.TRUMPF, "H10"),
        new Card(Color.HERZ, Type.ZEHN, Group.TRUMPF, "H10"));
    stich8.addCard(
        new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"),
        new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"),
        new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"),
        new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"));
      stich1.findHighestCard();
      stich2.findHighestCard();
      stich3.findHighestCard();
      stich4.findHighestCard();
      stich5.findHighestCard();
      stich6.findHighestCard();
      stich7.findHighestCard();
    stich8.findHighestCard();
      game.addRoundPoints(stich1);
      game.addRoundPoints(stich2);
      game.addRoundPoints(stich3);
      game.addRoundPoints(stich4);
      game.addRoundPoints(stich5);
      game.addRoundPoints(stich6);
      game.addRoundPoints(stich7);
      game.addRoundPoints(stich8);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(stich1.getWinnerPos()).isEqualTo(1);
          softly.assertThat(stich2.getWinnerPos()).isEqualTo(0);
          softly.assertThat(stich3.getWinnerPos()).isEqualTo(2);
          softly.assertThat(stich4.getWinnerPos()).isEqualTo(3);
          softly.assertThat(stich5.getWinnerPos()).isEqualTo(3);
          softly.assertThat(stich6.getWinnerPos()).isEqualTo(0);
          softly.assertThat(stich7.getWinnerPos()).isEqualTo(1);
          softly.assertThat(stich8.getWinnerPos()).isEqualTo(0);
          softly.assertThat(game.players.get(0).getPoints()).isEqualTo(90);
          softly.assertThat(game.players.get(1).getPoints()).isEqualTo(90);
          softly.assertThat(game.players.get(2).getPoints()).isEqualTo(54);
          softly.assertThat(game.players.get(3).getPoints()).isEqualTo(54);
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo("Re");
          softly.assertThat(game.players.get(1).getGroup()).isEqualTo("Re");
          softly.assertThat(game.players.get(2).getGroup()).isEqualTo("Kontra");
          softly.assertThat(game.players.get(3).getGroup()).isEqualTo("Kontra");
        });
  }

  @Test
  void testOneGameRound() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
    for (Player player : game.players) {
        player.setGroup();
    }

    for (int i = 0; i < 12; i++) {
      game.oneRound();
    }
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.get(0).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(1).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(2).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(3).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(0).getPoints()).isNotNull();
        });
  }

  @Test
  void testCardList() {
    DoppelkopfGame game = new DoppelkopfGame();

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.getTrumpCards()).isEmpty();
          softly.assertThat(game.getHerzCards()).isEmpty();
          softly.assertThat(game.getPikCards()).isEmpty();
          softly.assertThat(game.getKreuzCards()).isEmpty();
        });
  }

  @Test
  void testCardListModification() {
    DoppelkopfGame game = new DoppelkopfGame();

    List<String> trumpCards = game.getTrumpCards();
    List<String> herzCards = game.getHerzCards();
    List<String> pikCards = game.getPikCards();
    List<String> kreuzCards = game.getKreuzCards();

    trumpCards.add("Trump Card");
    herzCards.add("Herz Card");
    pikCards.add("Pik Card");
    kreuzCards.add("Kreuz Card");

    // Ensure the original lists in the game are still empty
    assertThat(game.getTrumpCards()).isEmpty();
    assertThat(game.getHerzCards()).isEmpty();
    assertThat(game.getPikCards()).isEmpty();
    assertThat(game.getKreuzCards()).isEmpty();
  }

  @Test
  void testHochzeit() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
    game.players.getFirst().setGroup();

    for (Player i : game.players) {
      for (int j = 0; j < i.getOwnCards().size(); j++) {
        if (i.getOwnCards().get(j).getShortcut().equals("KrD")) {
          i.removeCard(j);
          i.addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
        }
      }
    }
    game.players
        .getFirst()
        .addCard(
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"),
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    game.players.getFirst().setGroup();
    game.oneGame();
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo("Re");
          softly
              .assertThat(game.players.stream().filter(i -> i.getGroup().equals("Kontra")))
              .hasSize(2);
          softly
              .assertThat(game.players.stream().filter(i -> i.getGroup().equals("Re")))
              .hasSize(2);
        });
  }

  @Test
  void testHochzeit2() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.players
        .getFirst()
        .addCard(
            new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
            new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));

    game.players
        .get(1)
        .addCard(
            new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"),
            new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));

    game.players
        .get(2)
        .addCard(
            new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"),
            new Card(Color.PIK, Type.ASS, Group.PIK, "PA"));

    game.players
        .get(3)
        .addCard(
            new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"),
            new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));

    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    game.createDeck();
    for (Player i : game.players) {
      for (int j = 0; j < i.getOwnCards().size(); j++) {
        if (i.getOwnCards().get(j).getShortcut().equals("KrD")) {
          i.removeCard(j);
          i.addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
        }
      }
    }
    game.players
        .getFirst()
        .addCard(
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"),
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
      for (Player i : game.players) {
          i.setGroup();
      }
    game.oneGame();

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo("Re");
          softly.assertThat(game.players.get(1).getGroup()).isEqualTo("Kontra");
          softly.assertThat(game.players.get(2).getGroup()).isEqualTo("Re");
          softly.assertThat(game.players.get(3).getGroup()).isEqualTo("Kontra");
        });
  }

  @Test
  void testSortCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.getTrumpCards().add("K10");
    game.getHerzCards().add("H10");
    game.getPikCards().add("P10");
    game.getKreuzCards().add("K10");

    // Rufe die sortCards Methode auf
    game.sortCards(0);

    Card kreuzCard = new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"); // Trumpfkarte erstellen
    game.players.get(0).getOwnCards().add(kreuzCard);

    Card herzCard = new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"); // Trumpfkarte erstellen
    game.players.get(0).getOwnCards().add(herzCard);

    Card pikCard = new Card(Color.PIK, Type.NEUN, Group.PIK, "P9");
    game.players.get(0).getOwnCards().add(pikCard);

    Card trumpCard = new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD");
    game.players.get(0).getOwnCards().add(trumpCard);
    // Karte dem Spieler hinzufügen

    game.sortCards(0); // Sortiere die Karten des Spielers

    assertTrue(
        game.getKreuzCards().contains(kreuzCard.getShortcut()),
        "Kreuz card should be added to KreuzCards");
  }
}
