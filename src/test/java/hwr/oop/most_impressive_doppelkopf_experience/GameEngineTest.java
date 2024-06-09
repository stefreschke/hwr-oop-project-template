package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;

import hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames;
import org.junit.jupiter.api.Test;

import java.util.List;

import static hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames.CONTRA;
import static hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames.RE;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {
  @Test
  void startNewGameTest() {
    final Game game = new Game();
    game.addPlayer("Colin");
    game.addPlayer("Mihoshi");
    game.addPlayer("Joshi");
    game.addPlayer("Chris");

    distributeTeamsTest();

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));

    final List<Player> players = game.handOutCards();

    assertSoftly(
        softly -> {
          softly.assertThat(players.get(0).hand).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(players.get(1).hand).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(players.get(2).hand).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(players.get(3).hand).isNotEmpty().isNotNull().hasSize(12);
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
    assertEquals(4, game.players.size());
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
    game.setStartPlayer(game.players.getFirst());

    game.players
        .getFirst()
        .setHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0)));

    int playerHandSize = game.players.getFirst().getHand().size();
    int discardPileSize = game.stich.discardCards.size(); // discardPile.discardCards.size();

    game.playCard(game.players.getFirst().getHand().getFirst());

    assertSoftly(
        softly -> {
          softly.assertThat(playerHandSize - 1).isEqualTo(game.players.getFirst().getHand().size());
          softly.assertThat(discardPileSize + 1).isEqualTo(game.stich.discardCards.size());
        });
  }

  @Test
  void setNextPlayerTest() {
    var game = new Game();
    game.addPlayer("Stefan");
    game.addPlayer("Colin");

    game.setStartPlayer(game.players.getFirst());

    game.setNextPlayer();

    assertThat(game.activePlayer).isEqualTo(game.players.get(1));
  }

  @Test
  void calculateScoreTest() {
    final var game = new Game();
    // Score muss gesamt 240 ergeben!!!
  }

  @Test
  void FindWinningTeamTest() {
    final var game = new Game();
    final var player1 = new Player("player1", 30, 0);
    final var player2 = new Player("player2", 60, 1);
    final var player3 = new Player("player3", 60, 2);
    final var player4 = new Player("player4", 90, 3);
    player1.setTeam(TeamNames.RE);
    player2.setTeam(TeamNames.RE);
    player3.setTeam(TeamNames.CONTRA);
    player4.setTeam(TeamNames.CONTRA);

    game.players = List.of(player1, player2, player3, player4);

    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }

  @Test
  void distributeTeamsTest() {
    final var game = new Game();
    final var player1 = new Player("player1", 0, 0);
    final var player2 = new Player("player2", 0, 1);
    final var player3 = new Player("player3", 0, 2);
    final var player4 = new Player("player4", 0, 3);
    final List<Player> players = List.of(player1, player2, player3, player4);
    player1.getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    player2.getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    game.distributeTeams(players);
    assertThat(player1.getTeam()).isEqualTo(RE);
    assertThat(player2.getTeam()).isEqualTo(RE);
    assertThat(player3.getTeam()).isEqualTo(CONTRA);
    assertThat(player4.getTeam()).isEqualTo(CONTRA);
  }

  @Test
  void CardIsValidToPlayTest() {
    final var game = new Game();
    final var player1 = new Player("player1", 0, 0);
    final var player2 = new Player("player2", 0, 1);
    final var player3 = new Player("player3", 0, 2);
    final var player4 = new Player("player4", 0, 3);
    final List<Player> players = List.of(player1, player2, player3, player4);
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
    final var player3 = new Player("player3", 0, 2);
    final var player4 = new Player("player4", 0, 3);
    final List<Player> players = List.of(player1, player2, player3, player4);
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

    assertThat(game.schmeissen(game.players.getFirst())).isTrue();
  }


  @Test
  void schmeissenTestFalse() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "H9", 5)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "C9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "C9", 0)));

    assertThat(game.schmeissen(game.players.getFirst())).isFalse();
  }

  @Test
  void handOutCardsAreValidTestFiveNines() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "S9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "S9", 4)));

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();

              game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestFourNinesWithEveryColour() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "S9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "S9", 4)));

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "C9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();

              game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));

              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestFourNinesAndFourKings() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "S9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));


    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.HEARTS, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.CLUBS, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.CLUBS, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "S9", 0)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();

              game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isFalse();

              game.players.getFirst().getHand().removeLast();
              game.players.getFirst().getHand().removeLast();
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();

              game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "S9", 0)));
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();

              game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "S9", 0)));
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isFalse();
            });
  }

  @Test
  void handOutCardsAreValidTestLessThanTreeTrumpCards() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "C9", 4)));

    assertSoftly(
            softly -> {
              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isFalse();

              game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "S9", 10)));

              assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();
            });
  }

  @Test
  void handOutCardsAreValidTestSevenOrMoreFullCards() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "H9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "C9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "H9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.SPADES, 0, "C9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.CLUBS, 0, "H9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.HEARTS, 0, "H9", 11)));

    assertThat(game.handOutCardsAreValid(game.players.getFirst())).isFalse();
  }

  @Test
  void handOutCardsAreValidTestTrue() {
    var game = new Game();
    game.addPlayer("Mugtaba");

    game.setStartPlayer(game.players.getFirst());

    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.TRUMP, 0, "H9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 0, "C9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.SPADES, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "H9", 4)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "C9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.TRUMP, 0, "C9", 4)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.CLUBS, 0, "H9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.ACE, CardColours.CLUBS, 0, "H9", 11)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C9", 10)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.KING, CardColours.HEARTS, 0, "H9", 4)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.players.getFirst().addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));

    assertThat(game.handOutCardsAreValid(game.players.getFirst())).isTrue();
  }


  @Test
  void GameTest() {
    var game = new Game();
    game.addPlayer("Mugtaba");
    game.addPlayer("Simon");
    game.addPlayer("Galatea");
    game.addPlayer("Hajer");

    game.setStartPlayer(game.players.getFirst());

    game.players.get(0).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.players.get(0).addToHand(List.of(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 0, "CQ", 3)));
    game.players.get(1).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 0, "D9", 0)));
    game.players.get(1).addToHand(List.of(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 0, "CQ", 3)));
    game.players.get(2).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.CLUBS, 0, "C9", 0)));
    game.players.get(2).addToHand(List.of(new Card(CardSymbols.TEN, CardColours.CLUBS, 0, "C10", 10)));
    game.players.get(3).addToHand(List.of(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0)));
    game.players.get(3).addToHand(List.of(new Card(CardSymbols.KING, CardColours.SPADES, 0, "SK", 4)));

    game.playCard(game.players.get(0).getHand().get(0));
    game.playCard(game.players.get(1).getHand().get(1));
    game.playCard(game.players.get(2).getHand().get(0));
    game.playCard(game.players.get(3).getHand().get(0));

    game.evaluateRound();

    game.playCard(game.players.get(0).getHand().get(0));
    game.playCard(game.players.get(1).getHand().get(0));
    game.playCard(game.players.get(2).getHand().get(0));
    game.playCard(game.players.get(3).getHand().get(0));

    game.evaluateRound();

    game.evaluateGame();

    assertThat(game.findWinningTeam()).isEqualTo(CONTRA);
  }
}