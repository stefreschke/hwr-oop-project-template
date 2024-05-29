package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoppelkopfGameTest {
  @Test
  void testOneRound() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
    int winner = game.oneRound();
    assertThat(winner).isBetween(1, 4);
  }

  @Test
  void testOneRoundWithTrump() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.players.get(0).addCard(new Card(Color.HERZ, Type.NEUN, false, "H9"));
    game.players.get(0).addCard(new Card(Color.KREUZ, Type.BUBE, true, "KrB"));
    game.players.get(1).addCard(new Card(Color.PIK, Type.ZEHN, false, "P10"));
    game.players.get(1).addCard(new Card(Color.PIK, Type.ASS, false, "PA"));
    game.players.get(2).addCard(new Card(Color.KARO, Type.KOENIG, true, "KK"));
    game.players.get(2).addCard(new Card(Color.KARO, Type.BUBE, true, "KB"));
    game.players.get(3).addCard(new Card(Color.HERZ, Type.KOENIG, false, "HK"));
    game.players.get(3).addCard(new Card(Color.KARO, Type.DAME, true, "KD"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly
              .assertThat(
                  game.players
                      .getFirst()
                      .checkCard(Color.HERZ, game.players.getFirst().getOwnCards().get(1)))
              .isFalse();
          softly
              .assertThat(
                  game.players
                      .getFirst()
                      .checkCard(Color.HERZ, game.players.getFirst().getOwnCards().getFirst()))
              .isTrue();
          softly
              .assertThat(
                  game.players.getFirst().checkCard(game.players.getFirst().getOwnCards().get(1)))
              .isTrue();
          softly
              .assertThat(
                  game.players
                      .getFirst()
                      .checkCard(game.players.getFirst().getOwnCards().getFirst()))
              .isFalse();
          assertThat(game.oneRound()).isEqualTo(3);
          assertThat(game.oneRound()).isEqualTo(4);
        });
  }

  @Test
  void testFindHighestCard() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
    game.players.get(0).setGroup("Re");
    game.players.get(1).setGroup("Re");
    game.players.get(2).setGroup("Kontra");
    game.players.get(3).setGroup("Kontra");

    List<Card> testList1 =
        List.of(
            new Card(Color.HERZ, Type.ASS, false, "HA"),
            new Card(Color.HERZ, Type.BUBE, true, "HB"),
            new Card(Color.PIK, Type.ASS, false, "PA"));
    List<Card> testList2 =
        List.of(
            new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"),
            new Card(Color.KREUZ, Type.NEUN, false, "Kr9"));
    List<Card> testList3 =
        List.of(
            new Card(Color.PIK, Type.ZEHN, false, "P10"),
            new Card(Color.PIK, Type.ZEHN, false, "P10"),
            new Card(Color.PIK, Type.ASS, false, "PA"),
            new Card(Color.PIK, Type.NEUN, false, "P9"));
    List<Card> testList4 =
        List.of(
            new Card(Color.KREUZ, Type.KOENIG, false, "KrK"),
            new Card(Color.HERZ, Type.NEUN, false, "H9"),
            new Card(Color.PIK, Type.NEUN, false, "P9"),
            new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"));
    List<Card> testList5 =
        List.of(
            new Card(Color.KREUZ, Type.BUBE, false, "KrB"),
            new Card(Color.KREUZ, Type.BUBE, false, "KrB"),
            new Card(Color.KREUZ, Type.BUBE, false, "KrB"),
            new Card(Color.KREUZ, Type.DAME, false, "KrD"));
    List<Card> testList6 =
        List.of(
            new Card(Color.HERZ, Type.ASS, false, "HA"),
            new Card(Color.HERZ, Type.NEUN, false, "H9"),
            new Card(Color.HERZ, Type.ASS, false, "HA"));
    List<Card> testList7 =
        List.of(
            new Card(Color.HERZ, Type.ZEHN, true, "H10"),
            new Card(Color.HERZ, Type.ZEHN, true, "H10"));
    List<Card> testList8 =
        List.of(
            new Card(Color.HERZ, Type.NEUN, false, "H9"),
            new Card(Color.PIK, Type.ZEHN, false, "P10"),
            new Card(Color.KREUZ, Type.NEUN, false, "Kr9"),
            new Card(Color.KREUZ, Type.KOENIG, false, "KrK"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList1))).isEqualTo(2);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList2))).isEqualTo(1);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList3))).isEqualTo(3);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList4))).isEqualTo(4);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList5))).isEqualTo(4);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList6))).isEqualTo(1);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList7))).isEqualTo(2);
          softly.assertThat(game.findHighestCard(new ArrayList<>(testList8))).isEqualTo(1);
          softly.assertThat(game.players.get(1).getPoints()).isEqualTo(90);
          softly.assertThat(game.players.get(0).getPoints()).isEqualTo(90);
          softly.assertThat(game.players.get(2).getPoints()).isEqualTo(54);
          softly.assertThat(game.players.get(3).getPoints()).isEqualTo(54);
        });
  }

  @Test
  void testOneGameRound() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
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
    void testSortCards(){
      DoppelkopfGame game = new DoppelkopfGame();
      game.getTrumpCards().add("K10");
      game.getHerzCards().add("H10");
      game.getPikCards().add("P10");
      game.getKreuzCards().add("K10");

      // Rufe die sortCards Methode auf
      game.sortCards(0);

      // Überprüfe, ob die Listen leer sind
      assertTrue(game.getTrumpCards().isEmpty(), "trumpCards should be empty");
      assertTrue(game.getHerzCards().isEmpty(), "herzCards should be empty");
      assertTrue(game.getPikCards().isEmpty(), "pikCards should be empty");
      assertTrue(game.getKreuzCards().isEmpty(), "kreuzCards should be empty");



      Card trumpCard = new Card(Color.KARO,Type.ZEHN, true,"K10" ); // Trumpfkarte erstellen
      game.players.get(0).getOwnCards().add(trumpCard);

      Card herzCard= new Card(Color.HERZ,Type.NEUN, false,"H9" ); // Trumpfkarte erstellen
      game.players.get(0).getOwnCards().add(herzCard);

      Card pikCard = new Card(Color.PIK,Type.NEUN, false,"P9" );
      game.players.get(0).getOwnCards().add(pikCard);

      Card kreuzCard = new Card(Color.KREUZ,Type.NEUN, false,"K9" );
      game.players.get(0).getOwnCards().add(kreuzCard);
      // Karte dem Spieler hinzufügen

      game.sortCards(0); // Sortiere die Karten des Spielers

      assertTrue(game.getTrumpCards().contains(trumpCard.getShortcut()), "Trump card should be added to trumpCards");
        //hier folgen noch assertTrues...
  }



}

