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
    final var player = new Player("player1", 0, 0);
    final var discardPile = new DiscardPile();

    List<Card> hand = List.of(new Card(CardSymbols.NINE, CardColours.TRUMP, 10, "D9", 0));
    player.setHand(hand);

    int playerHandSize = player.getHand().size();
    int discardPileSize = discardPile.discardCards.size();

    game.playCard(player.getHand().get(0));

    assertSoftly(
            softly -> {
              softly.assertThat(playerHandSize - 1).isEqualTo(player.getHand().size());

              softly.assertThat(discardPileSize + 1).isEqualTo(discardPile.discardCards.size());
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
