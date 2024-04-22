package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class GameEngineTest {

    @Test
    void handOutCardsTest() {
        final var game = new Game();
        final var players = game.handOutCards();

        assertThat(players.get(0).hand)
                .isNotEmpty()
                .isNotNull()
                .hasSize(12);

        assertThat(players.get(1).hand)
                .isNotEmpty()
                .isNotNull()
                .hasSize(12);

        assertThat(players.get(2).hand)
                .isNotEmpty()
                .isNotNull()
                .hasSize(12);

        assertThat(players.get(3).hand)
                .isNotEmpty()
                .isNotNull()
                .hasSize(12);
    }
}
