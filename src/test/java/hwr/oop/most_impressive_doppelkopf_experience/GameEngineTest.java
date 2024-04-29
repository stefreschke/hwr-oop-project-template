package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class GameEngineTest {

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

    //Scenario erstellen
    game.activePlayer = new Player("player1", 0, 0);
    game.discardPile = new DiscardPile();
    List<Card> hand = List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    game.activePlayer.setHand(hand);

    int playerHandSize = game.activePlayer.getHand().size();
    int discardPileSize = game.discardPile.discardCards.size();

    game.playCard(game.activePlayer.getHand().get(0));


    assertSoftly(
            softly -> {
              softly.assertThat(game.activePlayer.getHand()).hasSize(playerHandSize - 1);

              softly.assertThat(game.discardPile.discardCards).hasSize(discardPileSize + 1);
            });
  }
  @Test
    void calculateScoreTest() {
      final var game = new Game();
      //Score muss gesamt 240 ergeben!!!
  }
  @Test
    void distributeTeams(){
      final var game = new Game();
      final var player1 = new Player("player1", 0, 0);



  }
}
