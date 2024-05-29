package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
  @Test
  void testCreatePlayer() {
    DoppelkopfGame game = new DoppelkopfGame();

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.get(0).getName()).isEqualTo("Spieler1");
          softly.assertThat(game.players.get(1).getName()).isEqualTo("Spieler2");
          softly.assertThat(game.players.get(2).getName()).isEqualTo("Spieler3");
          softly.assertThat(game.players.get(3).getName()).isEqualTo("Spieler4");

          softly.assertThat(game.players.get(0).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(1).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(2).getOwnCards()).isEmpty();
          softly.assertThat(game.players.get(3).getOwnCards()).isEmpty();

          softly.assertThat(game.players.get(0).getOrder()).isEqualTo(1);
          softly.assertThat(game.players.get(1).getOrder()).isEqualTo(2);
          softly.assertThat(game.players.get(2).getOrder()).isEqualTo(3);
          softly.assertThat(game.players.get(3).getOrder()).isEqualTo(4);
        });
  }

  @Test
  void checkPlayerHands() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.get(0).getOwnCards()).hasSize(12);
          softly.assertThat(game.players.get(1).getOwnCards()).hasSize(12);
          softly.assertThat(game.players.get(2).getOwnCards()).hasSize(12);
          softly.assertThat(game.players.get(3).getOwnCards()).hasSize(12);
        });
  }

  @Test
  void testPoints() {
    Player player1 = new Player("Spieler1", 1, 0);
    Player player2 = new Player("Spieler2", 2, 27);
    Player player3 = new Player("Spieler3", 3, 33);
    Player player4 = new Player("Spieler4", 4, 12);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(player1.getPoints()).isEqualTo(0);
          softly.assertThat(player2.getPoints()).isEqualTo(27);
          softly.assertThat(player3.getPoints()).isEqualTo(33);
          softly.assertThat(player4.getPoints()).isEqualTo(12);
        });
  }

  @Test
  void testCheckCard() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
    game.players.get(1).addCard(new Card(Color.HERZ, Type.ASS, false, "HA"));
    game.players.get(2).addCard(new Card(Color.HERZ, Type.BUBE, true, "HB"));
    Player player1 = new Player("player1", 1, 0);
    Player player2 = new Player("player2", 2, 27);
    player1.addCard(new Card(Color.HERZ, Type.DAME, true, "HD"));
    player2.addCard(new Card(Color.HERZ, Type.ASS, false, "HD"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly
              .assertThat(
                  game.players
                      .getFirst()
                      .checkCard(Color.PIK, new Card(Color.PIK, Type.NEUN, false, "P9")))
              .isTrue();
          softly
              .assertThat(
                  game.players
                      .get(1)
                      .checkCard(Color.HERZ, new Card(Color.PIK, Type.NEUN, false, "P9")))
              .isFalse();
          softly
              .assertThat(
                  game.players.get(2).checkCard(new Card(Color.PIK, Type.NEUN, false, "P9")))
              .isFalse();
          softly
              .assertThat(game.players.get(2).checkCard(new Card(Color.PIK, Type.DAME, true, "P9")))
              .isTrue();
          softly.assertThat(player1.checkCard(new Card(Color.PIK, Type.DAME, true, "P9"))).isTrue();
          softly
              .assertThat(player1.checkCard(new Card(Color.PIK, Type.NEUN, false, "P9")))
              .isFalse();
          softly
              .assertThat(player2.checkCard(new Card(Color.PIK, Type.ZEHN, false, "P9")))
              .isTrue();
          softly
              .assertThat(player2.checkCard(Color.PIK, new Card(Color.PIK, Type.ZEHN, false, "P9")))
              .isTrue();
          softly
              .assertThat(
                  player2.checkCard(Color.PIK, new Card(Color.KREUZ, Type.ZEHN, false, "P9")))
              .isTrue();
        });
  }

  @Test
  void testSetGroup() {
    Player player1 = new Player("player1", 1, 0);
    Player player2 = new Player("player2", 1, 0);
    player1.setGroup("Re");
    player2.setGroup("Kontra");

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(player1.getGroup()).isEqualTo("Re");
          softly.assertThat(player2.getGroup()).isEqualTo("Kontra");
        });
  }

  @Test
  void testSetGroupWithCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.dealCards(game.shuffleDeck(game.initializeCards()));
    int countRe = 0;
    int coutKontra = 0;
    for (Player player : game.players) {
      player.setGroup();
      if (player.getGroup().equals("Re")) {
        countRe++;
      } else {
        coutKontra++;
      }
    }

    int finalCountRe = countRe;
    int finalCoutKontra = coutKontra;
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(finalCountRe).isBetween(1, 2);
          softly.assertThat(finalCoutKontra).isBetween(2, 3);
        });
  }

  @Test
  void testCountPlayersTrumpCards() {
    Player player1 = new Player("player1", 1, 0);

    player1.addCard(new Card(Color.HERZ, Type.DAME, true, "HD"));
    player1.addCard(new Card(Color.KARO, Type.NEUN, true, "Ka9"));
    player1.addCard(new Card(Color.PIK, Type.BUBE, true, "PB"));
    player1.addCard(new Card(Color.HERZ, Type.KOENIG, false, "HK"));
    player1.addCard(new Card(Color.KREUZ, Type.ZEHN, false, "Kr10"));

    assertThat(player1.countPlayersTrumpCards()).isEqualTo(3);
  }
}
