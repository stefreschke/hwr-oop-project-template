package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;

import org.junit.jupiter.api.Test;

import java.util.List;

import static hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames.CONTRA;
import static hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames.RE;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.Assertions.assertThat;


class GameEngineTest {
  @Test
  void startNewGameTest() {
    final Game game = new Game();
    game.startNewGame();
    handOutCardsTest();
    distributeTeamsTest();
    gameLoopTest();
  }
  @Test
  void handOutCardsTest() {
    final var game = new Game();
    final var players = game.handOutCards();

    assertSoftly(
        softly -> {
          softly.assertThat(players.get(0).hand).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(players.get(1).hand).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(players.get(2).hand).isNotEmpty().isNotNull().hasSize(12);

          softly.assertThat(players.get(3).hand).isNotEmpty().isNotNull().hasSize(12);
        });
  }

  @Test
  void playCardTest() {
    final var game = new Game();
    final var player = new Player("player1", 0, 0);

    List<Card> hand = List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    player.setHand(hand);

    int playerHandSize = player.getHand().size();
    int discardPileSize = game.discardPile.discardCards.size();//discardPile.discardCards.size();

    game.playCard(player.getHand().getFirst(), player);

    assertSoftly(
            softly -> {
              softly.assertThat(playerHandSize - 1).isEqualTo(player.getHand().size());
              softly.assertThat(discardPileSize +1).isEqualTo(game.discardPile.discardCards.size());
            });
    }
  @Test
    void gameLoopTest() {
    final var game = new Game();
    List<Card> hand = List.of(new Card(CardSymbols.TEN, CardColours.TRUMP, 100, "H10", 0));
    game.players().getFirst().setHand(hand);
    game.players.get(0).setTeam(RE);
    game.players.get(1).setTeam(RE);
    game.players.get(2).setTeam(CONTRA);
    game.players.get(3).setTeam(CONTRA);
    game.gameLoop();
    assertThat(game.players().getFirst().getHand()).isEmpty();
  }

  @Test
     void calculateScoreTest() {
      final var game = new Game();
      //Score muss gesamt 240 ergeben!!!
  }
  @Test
    void distributeTeamsTest(){
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
    void CardIsValidToPlayTest(){
    final var game = new Game();
    final var player1 = new Player("player1", 0, 0);
    final var player2 = new Player("player2", 0, 1);
    final var player3 = new Player("player3", 0, 2);
    final var player4 = new Player("player4", 0, 3);
    final List<Player> players = List.of(player1, player2, player3, player4);
    player1.getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    final var fixDiscardPile = game.discardPile;
    fixDiscardPile.discardCard(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    assertThat(game.cardIsValidToBePlayed(player1.getHand().getFirst(),player1,fixDiscardPile)).isTrue();

  }
  @Test
  void CardIsNotValidToPlayTest(){
    final var game = new Game();
    final var player1 = new Player("player1", 0, 0);
    final var player2 = new Player("player2", 0, 1);
    final var player3 = new Player("player3", 0, 2);
    final var player4 = new Player("player4", 0, 3);
    final List<Player> players = List.of(player1, player2, player3, player4);
    player1.getHand().add(new Card(CardSymbols.NINE, CardColours.HEARTS, 0, "H9", 0));
    player1.getHand().add(new Card(CardSymbols.QUEEN, CardColours.TRUMP, 21, "CQ", 3));
    player2.getHand().add(new Card(CardSymbols.ACE, CardColours.CLUBS, 11, "CA", 11));
    final var fixDiscardPile = game.discardPile;
    fixDiscardPile.discardCard(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    assertThat(game.cardIsValidToBePlayed(player1.getHand().getFirst(),player1,fixDiscardPile)).isFalse();
    assertThat(game.cardIsValidToBePlayed(player1.getHand().getFirst(),player2,fixDiscardPile)).isFalse();
  }

}

