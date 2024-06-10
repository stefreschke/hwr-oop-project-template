package hwr.oop.doppelkopf.group6;

import static org.assertj.core.api.Assertions.assertThat;

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
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(winner).isBetween(1, 4);
          softly.assertThat(game.players.get(winner - 1).getPoints()).isNotEqualTo(0);
        });
    assertThat(winner).isBetween(1, 4);
  }

  @Test
  void testOneRoundWithTrump() {
    DoppelkopfGame game = new DoppelkopfGame();
    Stich stich1 = new Stich();
    Stich stich2 = new Stich();
    stich1.addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
    stich2.addCard(new Card(Color.HERZ, Type.DAME, Group.TRUMPF, "HD"));
    game.players.get(0).getHand().addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
    game.players.get(0).getHand().addCard(new Card(Color.KREUZ, Type.BUBE, Group.TRUMPF, "KrB"));
    game.players.get(1).getHand().addCard(new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));
    game.players.get(1).getHand().addCard(new Card(Color.PIK, Type.ASS, Group.PIK, "PA"));
    game.players.get(2).getHand().addCard(new Card(Color.KARO, Type.KOENIG, Group.TRUMPF, "KK"));
    game.players.get(2).getHand().addCard(new Card(Color.KARO, Type.BUBE, Group.TRUMPF, "KB"));
    game.players.get(3).getHand().addCard(new Card(Color.HERZ, Type.KOENIG, Group.HERZ, "HK"));
    game.players.get(3).getHand().addCard(new Card(Color.KARO, Type.DAME, Group.TRUMPF, "KD"));

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
          softly.assertThat(game.players.getFirst().getHand().getTrumpCards()).isEmpty();
          softly.assertThat(game.players.getFirst().getHand().getHerzCards()).isEmpty();
          softly.assertThat(game.players.getFirst().getHand().getPikCards()).isEmpty();
          softly.assertThat(game.players.getFirst().getHand().getKreuzCards()).isEmpty();
        });
  }

  @Test
  void testCardListModification() {
    DoppelkopfGame game = new DoppelkopfGame();

    List<String> trumpCards = game.players.getFirst().getHand().getTrumpCards();
    List<String> herzCards = game.players.getFirst().getHand().getHerzCards();
    List<String> pikCards = game.players.getFirst().getHand().getPikCards();
    List<String> kreuzCards = game.players.getFirst().getHand().getKreuzCards();

    trumpCards.add("Trump Card");
    herzCards.add("Herz Card");
    pikCards.add("Pik Card");
    kreuzCards.add("Kreuz Card");

    // Ensure the original lists in the game are still empty
    assertThat(game.players.getFirst().getHand().getTrumpCards()).isEmpty();
    assertThat(game.players.getFirst().getHand().getHerzCards()).isEmpty();
    assertThat(game.players.getFirst().getHand().getPikCards()).isEmpty();
    assertThat(game.players.getFirst().getHand().getKreuzCards()).isEmpty();
  }

  @Test
  void testHochzeit() {
    DoppelkopfGame game = new DoppelkopfGame();
    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    deck.dealCards(game.players);

    for (Player i : game.players) {
      for (int j = 0; j < i.getOwnCards().size(); j++) {
        if (i.getOwnCards().get(j).getShortcut().equals("KrD")) {
          i.getHand().removeCard(j);
          i.getHand().addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
        }
      }
    }
    game.players
        .getFirst()
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"),
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    game.setPlayerGroups();
    for (int i = 0; i < 12; i++) {
      game.oneGame();
    }
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo(PlayerGroup.RE);
          softly
              .assertThat(
                  game.players.stream().filter(i -> i.getGroup().equals(PlayerGroup.KONTRA)))
              .hasSize(2);
          softly
              .assertThat(game.players.stream().filter(i -> i.getGroup().equals(PlayerGroup.RE)))
              .hasSize(2);
        });
  }

  @Test
  void testHochzeit2() {
    DoppelkopfGame game = new DoppelkopfGame();
    game.players
        .getFirst()
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.ASS, Group.KREUZ, "KrA"),
            new Card(Color.PIK, Type.ZEHN, Group.PIK, "P10"));

    game.players
        .get(1)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.ZEHN, Group.KREUZ, "Kr10"),
            new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));

    game.players
        .get(2)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.NEUN, Group.KREUZ, "Kr9"),
            new Card(Color.PIK, Type.NEUN, Group.PIK, "P9"));

    game.players
        .get(3)
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.KOENIG, Group.KREUZ, "KrK"),
            new Card(Color.PIK, Type.ASS, Group.PIK, "PA"));

    Deck deck = new Deck();
    deck.initializeCards();
    deck.shuffleDeck();
    game.createDeck();
    for (Player i : game.players) {
      for (int j = 0; j < i.getOwnCards().size(); j++) {
        if (i.getOwnCards().get(j).getShortcut().equals("KrD")) {
          i.getHand().removeCard(j);
          i.getHand().addCard(new Card(Color.HERZ, Type.NEUN, Group.HERZ, "H9"));
        }
      }
    }
    game.players
        .getFirst()
        .getHand()
        .addCard(
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"),
            new Card(Color.KREUZ, Type.DAME, Group.TRUMPF, "KrD"));
    for (Player i : game.players) {
      i.setGroup();
    }

    for (int i = 0; i < game.players.getFirst().getOwnCards().size(); i++) {
      game.oneGame();
    }

    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(game.players.getFirst().getGroup()).isEqualTo(PlayerGroup.RE);
          softly.assertThat(game.players.get(1).getGroup()).isEqualTo(PlayerGroup.KONTRA);
          softly.assertThat(game.players.get(2).getGroup()).isEqualTo(PlayerGroup.KONTRA);
          softly.assertThat(game.players.get(3).getGroup()).isEqualTo(PlayerGroup.RE);
        });
  }

  @Test
  void testcreateDeck(){
    DoppelkopfGame game = new DoppelkopfGame();
    game.createDeck();

    assertThat(game.deck.getCards()).isEmpty();
  }
}
