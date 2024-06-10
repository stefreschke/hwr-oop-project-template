package hwr.oop.doppelkopf.group6;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class StichTest {
  @Test
  void testFindHighestCard() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
    game.players.get(0).setGroup(PlayerGroup.RE);
    game.players.get(1).setGroup(PlayerGroup.RE);
    game.players.get(2).setGroup(PlayerGroup.KONTRA);
    game.players.get(3).setGroup(PlayerGroup.KONTRA);

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
    Stich stich9 = new Stich();
    Stich stich10 = new Stich();
      Stich stich11 = new Stich();


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
    stich9.addCard(
        new Card(Color.PIK, Type.DAME, Group.TRUMPF, "PD"),
        new Card(Color.KARO, Type.DAME, Group.TRUMPF, "KaD"));
    stich10.addCard(
        new Card(Color.PIK, Type.DAME, Group.TRUMPF, "PD"),
        new Card(Color.PIK, Type.DAME, Group.TRUMPF, "PD"));
      stich11.addCard(
              new Card(Color.PIK, Type.DAME, Group.TRUMPF, "PD"),
              new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    stich1.findHighestCard();
    stich2.findHighestCard();
    stich3.findHighestCard();
    stich4.findHighestCard();
    stich5.findHighestCard();
    stich6.findHighestCard();
    stich7.findHighestCard();
    stich8.findHighestCard();
    stich9.findHighestCard();
    stich10.findHighestCard();
    stich11.findHighestCard();
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
          softly.assertThat(stich9.getWinnerPos()).isEqualTo(0);
          softly.assertThat(stich10.getWinnerPos()).isEqualTo(0);
            softly.assertThat(stich11.getWinnerPos()).isEqualTo(1);
          softly.assertThat(game.players.get(0).getPoints()).isEqualTo(90);
          softly.assertThat(game.players.get(1).getPoints()).isEqualTo(90);
          softly.assertThat(game.players.get(2).getPoints()).isEqualTo(54);
          softly.assertThat(game.players.get(3).getPoints()).isEqualTo(54);
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(game.players.get(1).getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(game.players.get(2).getGroup()).isEqualTo(PlayerGroup.KONTRA);
          softly.assertThat(game.players.get(3).getGroup()).isEqualTo(PlayerGroup.KONTRA);
        });
  }
}
