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
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);

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
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    game.players.get(1).getHand().addCard(new Card(Color.HERZ, Type.ASS, Group.HERZ, "HA"));
    game.players.get(2).getHand().addCard(new Card(Color.HERZ, Type.BUBE, Group.TRUMPF, "HB"));
    Player player1 = new Player("player1", 1, 0);
    Player player2 = new Player("player2", 2, 27);
    player1.getHand().addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    player2.getHand().addCard(new Card(Color.HERZ, Type.ASS, Group.HERZ, "HD"));
    Stich stich1 = new Stich();
    stich1.addCard(new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));
    Stich stich2 = new Stich();
    stich2.addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
    Stich stich3 = new Stich();
    stich3.addCard(new Card(Color.PIK, Type.BUBE, Group.TRUMPF, "PB"));

    SoftAssertions.assertSoftly(
        softly -> {
          softly
              .assertThat(
                  stich1.checkCard(
                      game.players.get(0).getOwnCards(),
                      new Card(Color.PIK, Type.NEUN, Group.PIK, "P9")))
              .isTrue();
          softly
              .assertThat(
                  stich2.checkCard(
                      game.players.get(1).getOwnCards(),
                      new Card(Color.PIK, Type.NEUN, Group.PIK, "P9")))
              .isFalse();
          softly
              .assertThat(
                  stich3.checkCard(
                      game.players.get(2).getOwnCards(),
                      new Card(Color.PIK, Type.NEUN, Group.PIK, "P9")))
              .isFalse();
          softly
              .assertThat(
                  stich3.checkCard(
                      game.players.get(2).getOwnCards(),
                      new Card(Color.PIK, Type.DAME, Group.TRUMPF, "P9")))
              .isTrue();
          softly
              .assertThat(
                  stich3.checkCard(
                      player1.getOwnCards(), new Card(Color.PIK, Type.DAME, Group.TRUMPF, "P9")))
              .isTrue();
          softly
              .assertThat(
                  stich3.checkCard(
                      player1.getOwnCards(), new Card(Color.PIK, Type.NEUN, Group.PIK, "P9")))
              .isFalse();
          softly
              .assertThat(
                  stich1.checkCard(
                      player2.getOwnCards(), new Card(Color.PIK, Type.ZEHN, Group.PIK, "P9")))
              .isTrue();
          softly
              .assertThat(
                  stich1.checkCard(
                      player2.getOwnCards(), new Card(Color.PIK, Type.ZEHN, Group.PIK, "P9")))
              .isTrue();
          softly
              .assertThat(
                  stich1.checkCard(
                      player2.getOwnCards(), new Card(Color.KREUZ, Type.ZEHN, Group.PIK, "P9")))
              .isTrue();
        });
  }

  @Test
  void testSetGroup() {
    Player player1 = new Player("player1", 1, 0);
    Player player2 = new Player("player2", 1, 0);
    Player player3 = new Player("player3", 1, 0);
    player1.setGroup(PlayerGroup.RE);
    player2.setGroup(PlayerGroup.KONTRA);
    player3.setGroup(PlayerGroup.HOCHZEIT);

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(player1.getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(player2.getGroup()).isEqualTo(PlayerGroup.KONTRA);
          softly.assertThat(player3.getGroup()).isEqualTo(PlayerGroup.HOCHZEIT);
        });
  }

  @Test
  void testSetGroupWithCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
    int countRe = 0;
    int countKontra = 0;
    int countHochzeit = 0;
    for (Player player : game.players) {
      player.setGroup();

      switch (player.getGroup()) {
        case PlayerGroup.RE:
          countRe++;
          break;
        case PlayerGroup.KONTRA:
          countKontra++;
          break;
        case PlayerGroup.HOCHZEIT:
          countHochzeit++;
          break;
      }
    }
    int finalCountRe = countRe;
    int finalCountKontra = countKontra;
    int finalCountHochzeit = countHochzeit;
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(finalCountHochzeit).isBetween(0, 1);
          softly.assertThat(finalCountRe).isBetween(0, 2);
          softly.assertThat(finalCountKontra).isBetween(2, 3);
        });
  }

  @Test
  void testSetGroupWithLessCards() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.players.get(1).getHand().addCard(new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    game.players.get(2).getHand().addCard(new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    game.players.get(2).getHand().addCard(new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    for (Player player : game.players) {
      player.setGroup();
    }

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo(PlayerGroup.KONTRA);
          softly.assertThat(game.players.get(1).getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(game.players.get(2).getGroup()).isEqualTo(PlayerGroup.HOCHZEIT);
          softly.assertThat(game.players.get(3).getGroup()).isEqualTo(PlayerGroup.KONTRA);
        });
  }

  @Test
  void testRemoveCard() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);
    game.players.getFirst().getHand().removeCard(0);

    assertThat(game.players.getFirst().getOwnCards()).hasSize(11);
  }

  @Test
  void testCountPlayersTrumpCards() {
    Player player1 = new Player("player1", 1, 0);

    player1.getHand().addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    player1.getHand().addCard(new Card(Color.KARO, Type.NEUN, Group.TRUMPF, "Ka9"));
    player1.getHand().addCard(new Card(Color.PIK, Type.BUBE, Group.TRUMPF, "PB"));
    player1.getHand().addCard(new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    player1.getHand().addCard(new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"));

    assertThat(player1.getHand().countPlayersTrumpCards()).isEqualTo(3);
  }
}
