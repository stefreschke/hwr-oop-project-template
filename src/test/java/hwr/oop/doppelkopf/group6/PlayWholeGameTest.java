package hwr.oop.doppelkopf.group6;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class PlayWholeGameTest {
  @Test
  void playWholeGameTest() {
    DoppelkopfGame game = new DoppelkopfGame();
    Player player1 = game.players.get(0);
    Player player2 = game.players.get(1);
    Player player3 = game.players.get(2);
    Player player4 = game.players.get(3);
    game.players
        .get(0)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
            new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"),
            new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"),
            new Card(Color.PIK, Type.KOENIG, Group.PIK, "PK"),
            new Card(Color.HERZ, Type.ASS, Group.HERZ, "HA"),
            new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"),
            new Card(Color.KARO, Type.KOENIG, Group.TRUMPF, "KaK"),
            new Card(Color.KARO, Type.ASS, Group.TRUMPF, "KaA"),
            new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"),
            new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"),
            new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"),
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));

    game.players
        .get(1)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
            new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"),
            new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"),
            new Card(Color.PIK, Type.KOENIG, Group.PIK, "PK"),
            new Card(Color.HERZ, Type.ASS, Group.HERZ, "HA"),
            new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"),
            new Card(Color.KARO, Type.KOENIG, Group.TRUMPF, "KaK"),
            new Card(Color.KARO, Type.ASS, Group.TRUMPF, "KaA"),
            new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"),
            new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"),
            new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"),
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));

    game.players
        .get(2)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"),
            new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"),
            new Card(Color.PIK, Type.ASS, Group.PIK, "PA"),
            new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"),
            new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"),
            new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"),
            new Card(Color.KARO, Type.ZEHN, Group.TRUMPF, "Ka10"),
            new Card(Color.KARO, Type.BUBE, Group.TRUMPF, "KaB"),
            new Card(Color.PIK, Type.BUBE, Group.TRUMPF, "PB"),
            new Card(Color.KARO, Type.DAME, Group.TRUMPF, "KaD"),
            new Card(Color.PIK, Type.DAME, Group.TRUMPF, "PD"),
            new Card(Color.HERZ, Type.ZEHN, Group.TRUMPF, "H10"));

    game.players
        .get(3)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"),
            new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"),
            new Card(Color.PIK, Type.ASS, Group.PIK, "PA"),
            new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"),
            new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"),
            new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"),
            new Card(Color.KARO, Type.ZEHN, Group.TRUMPF, "Ka10"),
            new Card(Color.KARO, Type.BUBE, Group.TRUMPF, "KaB"),
            new Card(Color.PIK, Type.BUBE, Group.TRUMPF, "PB"),
            new Card(Color.KARO, Type.DAME, Group.TRUMPF, "KaD"),
            new Card(Color.PIK, Type.DAME, Group.TRUMPF, "PD"),
            new Card(Color.HERZ, Type.ZEHN, Group.TRUMPF, "H10"));

    game.setPlayerGroups();

    game.playCard(player1, "KrA")
        .playCard(player2, "Kr9")
        .playCard(player3, "KrK")
        .playCard(player4, "KrK")

        .playCard(player1, "HA")
        .playCard(player2, "H9")
        .playCard(player3, "HK")
        .playCard(player4, "HK")

        .playCard(player1, "PK")
        .playCard(player2, "P10")
        .playCard(player3, "PA")
        .playCard(player4, "P9")

        .playCard(player3, "Kr10")
        .playCard(player4, "Kr10")
        .playCard(player1, "Kr9")
        .playCard(player2, "KrA")

        .playCard(player2, "PK")
        .playCard(player3, "P9")
        .playCard(player4, "PA")
        .playCard(player1, "P10")

        .playCard(player4, "PB")
        .playCard(player1, "KrB")
        .playCard(player2, "KaK")
        .playCard(player3, "KaD")

        .playCard(player3, "KaB")
        .playCard(player4, "Ka9")
        .playCard(player1, "HB")
        .playCard(player2, "KrB")

        .playCard(player2, "HA")
        .playCard(player3, "PD")
        .playCard(player4, "KaB")
        .playCard(player1, "H9")

        .playCard(player3, "PB")
        .playCard(player4, "H10")
        .playCard(player1, "KaK")
        .playCard(player2, "HD")

        .playCard(player4, "Ka10")
        .playCard(player1, "KrD")
        .playCard(player2, "HB")
        .playCard(player3, "H10")

        .playCard(player3, "Ka9")
        .playCard(player4, "PD")
        .playCard(player1, "KaA")
        .playCard(player2, "KrD")

        .playCard(player2, "KaA")
        .playCard(player3, "Ka10")
        .playCard(player4, "KaD")
        .playCard(player1, "HD");

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(game.players.get(1).getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(game.players.get(2).getGroup()).isEqualTo(PlayerGroup.KONTRA);
          softly.assertThat(game.players.get(3).getGroup()).isEqualTo(PlayerGroup.KONTRA);

          softly.assertThat(game.players.getFirst().getPoints()).isEqualTo(138);
          softly.assertThat(game.players.get(1).getPoints()).isEqualTo(138);
          softly.assertThat(game.players.get(2).getPoints()).isEqualTo(102);
          softly.assertThat(game.players.get(3).getPoints()).isEqualTo(102);
        });
  }
}
