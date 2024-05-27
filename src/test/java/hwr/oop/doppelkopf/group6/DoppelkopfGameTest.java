package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class DoppelkopfGameTest {
  @Test
  void testOneRound() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
    int winner = game.oneRound();
    assertThat(winner).isBetween(1, 4);
  }

  @Test
  void testFindHighestCard() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
    game.players.get(0).addCard(new Card(Color.KREUZ, Type.DAME, true, "KrD"));
      game.players.get(1).addCard(new Card(Color.KREUZ, Type.DAME, true, "KrD"));
      for (Player player : game.players) {
          player.setGroup();
      }
    List<Card> testList1 = new ArrayList<>();
    List<Card> testList2 = new ArrayList<>();
    List<Card> testList3 = new ArrayList<>();
    List<Card> testList4 = new ArrayList<>();
    List<Card> testList5 = new ArrayList<>();
    List<Card> testList6 = new ArrayList<>();
    List<Card> testList7 = new ArrayList<>();
    List<Card> testList8 = new ArrayList<>();
    testList1.add(new Card(Color.HERZ, Type.ASS, false, "HA"));
    testList1.add(new Card(Color.HERZ, Type.BUBE, true, "HB"));
    testList1.add(new Card(Color.PIK, Type.ASS, false, "PA"));
    testList2.add(new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"));
    testList2.add(new Card(Color.KREUZ, Type.NEUN, false, "Kr9"));
    testList3.add(new Card(Color.PIK, Type.ZEHN, false, "P10"));
    testList3.add(new Card(Color.PIK, Type.ZEHN, false, "P10"));
    testList3.add(new Card(Color.PIK, Type.ASS, false, "PA"));
    testList3.add(new Card(Color.PIK, Type.NEUN, false, "P9"));
    testList4.add(new Card(Color.KREUZ, Type.KOENIG, false, "KrK"));
    testList4.add(new Card(Color.HERZ, Type.NEUN, false, "H9"));
    testList4.add(new Card(Color.PIK, Type.NEUN, false, "P9"));
    testList4.add(new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"));
    testList5.add(new Card(Color.KREUZ, Type.BUBE, false, "KrB"));
    testList5.add(new Card(Color.KREUZ, Type.BUBE, false, "KrB"));
    testList5.add(new Card(Color.KREUZ, Type.BUBE, false, "KrB"));
    testList5.add(new Card(Color.KREUZ, Type.DAME, false, "KrD"));
    testList6.add(new Card(Color.HERZ, Type.ASS, false, "HA"));
    testList6.add(new Card(Color.HERZ, Type.NEUN, false, "H9"));
    testList6.add(new Card(Color.HERZ, Type.ASS, false, "HA"));
    testList7.add(new Card(Color.HERZ, Type.ZEHN, true, "H10"));
    testList7.add(new Card(Color.HERZ, Type.ZEHN, true, "H10"));
    testList8.add(new Card(Color.HERZ, Type.NEUN, false, "H9"));
    testList8.add(new Card(Color.PIK, Type.ZEHN, false, "P10"));
    testList8.add(new Card(Color.KREUZ, Type.NEUN, false, "Kr9"));
    testList8.add(new Card(Color.KREUZ, Type.KOENIG, false, "KrK"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.findHighestCard(testList1)).isEqualTo(2);
          softly.assertThat(game.findHighestCard(testList2)).isEqualTo(1);
          softly.assertThat(game.findHighestCard(testList3)).isEqualTo(3);
          softly.assertThat(game.findHighestCard(testList4)).isEqualTo(4);
          softly.assertThat(game.findHighestCard(testList5)).isEqualTo(4);
          softly.assertThat(game.findHighestCard(testList6)).isEqualTo(1);
          softly.assertThat(game.findHighestCard(testList7)).isEqualTo(2);
          softly.assertThat(game.findHighestCard(testList8)).isEqualTo(1);
        });
  }

  @Test
  void testOneGameRound() {
    DoppelkopfGame game = new DoppelkopfGame();
      game.dealCards(game.shuffleDeck(game.initializeCards()));
      for (int i = 0; i<12; i++) {
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
}
