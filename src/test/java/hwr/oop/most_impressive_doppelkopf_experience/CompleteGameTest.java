package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import java.util.List;

import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.CONTRA;
import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.RE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class CompleteGameTest {
  @Test
  void FirstGameTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.ansagen(game.getPlayers().getFirst());

    game.getPlayers().getFirst().setTeam(RE);
    game.getPlayers().get(1).setTeam(RE);
    game.getPlayers().get(2).setTeam(CONTRA);
    game.getPlayers().get(3).setTeam(CONTRA);

    game.getPlayers()
        .get(0)
        .addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "C9", 0)));
    game.getPlayers()
        .get(1)
        .addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "CQ", 0)));
    game.getPlayers()
        .get(2)
        .addToHand(List.of(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "H9", 3)));
    game.getPlayers()
        .get(3)
        .addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));

    game.getPlayers()
        .get(0)
        .addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10)));
    game.getPlayers()
        .get(1)
        .addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers()
        .get(2)
        .addToHand(List.of(new Card(CardSymbols.KING, CardColours.CLUBS, 3, "H10", 4)));
    game.getPlayers()
        .get(3)
        .addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());
    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());
    // Galatea (Re) gewinnt 3 Punkte

    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());
    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());
    // Mugataba (Contra) gewinnt 14 Punkte

    assertSoftly(
        softAssertions -> {
          assertThat(game.getPlayers().getFirst().getScore()).isEqualTo(14);
          assertThat(game.getPlayers().getFirst().getCardsWon()).hasSize(4);
          assertThat(game.getPlayers().getFirst().getHand()).isEmpty();
          assertThat(game.getPlayers().getFirst().getPoints()).isEqualTo(8);

          assertThat(game.getPlayers().get(1).getScore()).isZero();
          assertThat(game.getPlayers().get(1).getCardsWon()).isEmpty();
          assertThat(game.getPlayers().get(1).getHand()).isEmpty();
          assertThat(game.getPlayers().get(1).getPoints()).isEqualTo(8);

          assertThat(game.getPlayers().get(2).getScore()).isEqualTo(3);
          assertThat(game.getPlayers().get(2).getCardsWon()).hasSize(4);
          assertThat(game.getPlayers().get(2).getHand()).isEmpty();
          assertThat(game.getPlayers().get(2).getPoints()).isEqualTo(5);

          assertThat(game.getPlayers().get(3).getScore()).isZero();
          assertThat(game.getPlayers().get(3).getCardsWon()).isEmpty();
          assertThat(game.getPlayers().get(3).getHand()).isEmpty();
          assertThat(game.getPlayers().get(3).getPoints()).isEqualTo(5);

          assertThat(game.findWinningTeam()).isEqualTo(RE);
        });
  }

  @Test
  void SecondGameTest() {
    Game game = new Game();

    game.addPlayer("Mausi");
    game.addPlayer("Schatz");
    game.addPlayer("Honigbienchen");
    game.addPlayer("Süßi");

    game.schmeissen(game.getPlayers().getFirst());

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers()
        .getFirst()
        .setHand(
            List.of(
                new Card(CardSymbols.ACE, CardColours.TRUMP, 4, "HA", 11), // value = 101
                new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0),
                new Card(CardSymbols.ACE, CardColours.TRUMP, 4, "HA", 11)) // value = 101
            );
    game.getPlayers()
        .get(1)
        .setHand(
            List.of(
                new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3),
                new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0),
                new Card(CardSymbols.TEN, CardColours.TRUMP, 12, "D10", 10)));
    game.getPlayers()
        .get(2)
        .setHand(
            List.of(
                new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3),
                new Card(CardSymbols.TEN, CardColours.SPADES, 1, "S10", 10),
                new Card(CardSymbols.JACK, CardColours.CLUBS, 17, "CJ", 2)));
    game.getPlayers()
        .get(3)
        .setHand(
            List.of(
                new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0),
                new Card(CardSymbols.NINE, CardColours.CLUBS, 21, "C9", 0),
                new Card(CardSymbols.KING, CardColours.CLUBS, 3, "KQ", 4)));

    game.distributeTeams();

    game.revaluePlayerWithTwoDiamondAces();
    game.ansagen(game.getPlayers().getFirst());

    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());
    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());
    // 0 gewinnt mit 17 Punkten

    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());
    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());
    // 2 gewinnt mit 10 Punkten

    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());
    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());
    // 0 gewinnt mit 27 Punkten

    assertSoftly(
        softAssertions -> {
          assertThat(game.getPlayers().getFirst().getScore()).isEqualTo(44);
          assertThat(game.getPlayers().getFirst().getCardsWon()).hasSize(8);
          assertThat(game.getPlayers().getFirst().getHand()).isEmpty();
          assertThat(game.getPlayers().getFirst().getPoints()).isEqualTo(10);
          assertThat(game.getPlayers().getFirst().getTeam()).isEqualTo(CONTRA);

          assertThat(game.getPlayers().get(1).getScore()).isZero();
          assertThat(game.getPlayers().get(1).getCardsWon()).isEmpty();
          assertThat(game.getPlayers().get(1).getHand()).isEmpty();
          assertThat(game.getPlayers().get(1).getPoints()).isEqualTo(3);
          assertThat(game.getPlayers().get(1).getTeam()).isEqualTo(RE);

          assertThat(game.getPlayers().get(2).getScore()).isEqualTo(10);
          assertThat(game.getPlayers().get(2).getCardsWon()).hasSize(4);
          assertThat(game.getPlayers().get(2).getHand()).isEmpty();
          assertThat(game.getPlayers().get(2).getPoints()).isEqualTo(3);
          assertThat(game.getPlayers().get(2).getTeam()).isEqualTo(RE);

          assertThat(game.getPlayers().get(3).getScore()).isZero();
          assertThat(game.getPlayers().get(3).getCardsWon()).isEmpty();
          assertThat(game.getPlayers().get(3).getHand()).isEmpty();
          assertThat(game.getPlayers().get(3).getPoints()).isEqualTo(10);
          assertThat(game.getPlayers().get(3).getTeam()).isEqualTo(CONTRA);

          assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
        });
  }
}
