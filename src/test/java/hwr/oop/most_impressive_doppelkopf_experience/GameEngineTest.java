package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.Test;

import java.util.List;

import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.CONTRA;
import static hwr.oop.most_impressive_doppelkopf_experience.TeamNames.RE;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import hwr.oop.most_impressive_doppelkopf_experience.Game;
class GameEngineTest {
  @Test
  void startNewGameTest() {
    final Game game = new Game();

    game.addPlayer("Colin");
    game.addPlayer("Mihoshi");
    game.addPlayer("Joshi");
    game.addPlayer("Chris");

    distributeTeamsTest();

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));

    game.handOutCards();

    assertSoftly(
        softly -> {
          softly.assertThat(game.getPlayers().get(0).getHand()).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(game.getPlayers().get(1).getHand()).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(game.getPlayers().get(2).getHand()).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(game.getPlayers().get(3).getHand()).isNotEmpty().isNotNull().hasSize(12);
        });
  }
  @Test
  void testAdd5Player() {
    final var game = new Game();
    game.addPlayer("player1");
    game.addPlayer("player2");
    game.addPlayer("player3");
    game.addPlayer("player4");
    game.addPlayer("player5");
    assertEquals(4, game.getPlayers().size());
  }

  @Test
  void AddPlayerTest() {
    final Game game = new Game();
    game.addPlayer("Colin");
    game.addPlayer("Mihoshi");
    game.addPlayer("Joshi");

    assertSoftly(
      softAssertions -> {
        softAssertions.assertThat(game.addPlayer("Chris")).isTrue();
        softAssertions.assertThat(game.addPlayer("Stefan")).isFalse();
      });
  }

  @Test
  void playCardTest() {
    final var game = new Game();
    game.addPlayer("Mihoshi");
    game.addPlayer("Colin");
    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers()
        .getFirst()
        .setHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0)));

    int playerHandSize = game.getPlayers().getFirst().getHand().size();
    int discardPileSize = game.stich.getDiscardCards().size(); // discardPile.discardCards.size();

    Player currentPlayer = game.activePlayer;

    game.playCard(game.getPlayers().getFirst().getHand().getFirst());

    assertSoftly(
        softly -> {
          softly.assertThat(playerHandSize - 1).isEqualTo(game.getPlayers().getFirst().getHand().size());
          softly.assertThat(discardPileSize + 1).isEqualTo(game.stich.getDiscardCards().size());
          softly.assertThat(currentPlayer).isNotEqualTo(game.activePlayer);
        });
  }

  @Test
  void setNextPlayerTest() {
    var game = new Game();
    game.addPlayer("Stefan");
    game.addPlayer("Colin");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.setNextPlayer();

    assertThat(game.activePlayer).isEqualTo(game.getPlayers().get(1));
  }

  @Test
  void FindWinningTeamTest() {
    final var game = new Game();
    game.addPlayer("1");
    game.addPlayer("2");
    game.addPlayer("3");
    game.addPlayer("0");

    game.getPlayers().getFirst().setTeam(TeamNames.RE);
    game.getPlayers().getFirst().setScore(30);
    game.getPlayers().get(1).setTeam(TeamNames.RE);
    game.getPlayers().get(1).setScore(30);
    game.getPlayers().get(2).setTeam(TeamNames.CONTRA);
    game.getPlayers().get(2).setScore(30);
    game.getPlayers().get(3).setTeam(TeamNames.CONTRA);
    game.getPlayers().get(3).setScore(30);


    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }

  @Test
  void distributeTeamsTest() {
    final var game = new Game();
    game.addPlayer("Simon");
    game.addPlayer("Stefan");
    game.addPlayer("Laura");
    game.addPlayer("Leo");

    game.getPlayers().getFirst().getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    game.getPlayers().get(1).getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    game.distributeTeams();

    assertThat(game.getPlayers().getFirst().getTeam()).isEqualTo(RE);
    assertThat(game.getPlayers().get(1).getTeam()).isEqualTo(RE);
    assertThat(game.getPlayers().get(2).getTeam()).isEqualTo(CONTRA);
    assertThat(game.getPlayers().get(3).getTeam()).isEqualTo(CONTRA);
  }

  @Test
  void CardIsValidToPlayTest() {
    final var game = new Game();
    final var player1 = new Player("player1", 0, 0);
    player1.getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    final var fixDiscardPile = game.stich;
    fixDiscardPile.discardCard(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    assertThat(game.cardIsValidToBePlayed(player1.getHand().getFirst(), player1, fixDiscardPile))
        .isTrue();
  }

  @Test
  void CardIsNotValidToPlayTest() {
    final var game = new Game();
    final var player1 = new Player("player1", 0, 0);
    final var player2 = new Player("player2", 0, 1);
    player1.getHand().add(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0));
    player1.getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    player2.getHand().add(new Card(CardSymbols.ACE, CardColours.CLUBS, 11, "CA", 11));
    final var fixDiscardPile = game.stich;
    fixDiscardPile.discardCard(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    assertThat(game.cardIsValidToBePlayed(player1.getHand().getFirst(), player1, fixDiscardPile))
        .isFalse();
    assertThat(game.cardIsValidToBePlayed(player1.getHand().getFirst(), player2, fixDiscardPile))
        .isFalse();
  }

  @Test
  void schmeissenTestTrue() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Mugtaba");

    assertSoftly(softAssertions -> {
      assertThat(game.schmeissen(game.getPlayers().getFirst())).isTrue();
      assertThat(game.getPlayers().getFirst().getHand()).hasSize(12);
    });
  }

  @Test
  void schmeissenTestFalse() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.getPlayers().getFirst());

    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "H9", 5)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "C9", 11)));
    game.getPlayers().getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "C9", 0)));

    assertThat(game.schmeissen(game.getPlayers().getFirst())).isFalse();
  }

  @Test
  void revaluePlayerWithTwoHeartAcesTest() {
    var game = new Game();
    game.addPlayer("ILoveCoconuts");
    game.addPlayer("Simon");

    game.getPlayers().getFirst().setHand(List.of(
            new Card(CardSymbols.ACE, CardColours.HEARTS, 4, "HA", 11),
            new Card(CardSymbols.KING, CardColours.HEARTS, 10, "HK", 4),
            new Card(CardSymbols.ACE, CardColours.SPADES, 4, "SA", 11)));
    game.revaluePlayerWithTwoHeartAces();

    assertSoftly(
            softly -> {
            assertThat(game.getPlayers().getFirst().getHand().getFirst().getValue()).isEqualTo(4);

              game.getPlayers().getFirst().setHand(List.of(
                      new Card(CardSymbols.ACE, CardColours.HEARTS, 4, "HA", 11),
                      new Card(CardSymbols.ACE, CardColours.HEARTS, 4, "HA", 11),
                      new Card(CardSymbols.KING, CardColours.HEARTS, 10, "HK", 4),
                      new Card(CardSymbols.ACE, CardColours.SPADES, 4, "SA", 11)));

            game.revaluePlayerWithTwoHeartAces();
            assertThat(game.getPlayers().getFirst().getHand().getFirst().getValue()).isEqualTo(101);
            assertThat(game.getPlayers().getFirst().getHand().get(1).getValue()).isEqualTo(101);
            assertThat(game.getPlayers().getFirst().getHand().get(2).getValue()).isEqualTo(10);
            assertThat(game.getPlayers().getFirst().getHand().get(3).getValue()).isEqualTo(4);

    });
  }

  @Test
  void GameTest() {
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

    game.getPlayers().get(0).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "C9", 0)));
    game.getPlayers().get(1).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "CQ", 0)));
    game.getPlayers().get(2).addToHand(List.of(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "H9", 3)));
    game.getPlayers().get(3).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));

    game.getPlayers().get(0).addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 10)));
    game.getPlayers().get(1).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.getPlayers().get(2).addToHand(List.of(new Card(CardSymbols.KING, CardColours.CLUBS, 3, "H10", 4)));
    game.getPlayers().get(3).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());
    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());

    game.evaluateRound();
    //Galatea (Re) gewinnt 3 Punkte

    game.playCard(game.getPlayers().get(2).getHand().getFirst());
    game.playCard(game.getPlayers().get(3).getHand().getFirst());
    game.playCard(game.getPlayers().get(0).getHand().getFirst());
    game.playCard(game.getPlayers().get(1).getHand().getFirst());

    game.evaluateRound();
    //Mugataba (Contra) gewinnt 14 Punkte

    game.evaluateGame();

    assertSoftly(softAssertions -> {
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
}