package hwr.oop.doppelkopf;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class GameEngineTest {

    @Test
    void handOutCardsTest() {
        final var game = new Game();
        final var players = game.handOutCards();

        assertSoftly(softly->{

            softly.assertThat(players.get(0).hand)
                    .isNotEmpty()
                    .isNotNull()
                    .hasSize(12);

            softly.assertThat(players.get(1).hand)
                    .isNotEmpty()
                    .isNotNull()
                    .hasSize(12);

            softly.assertThat(players.get(2).hand)
                    .isNotEmpty()
                    .isNotNull()
                    .hasSize(12);

            softly.assertThat(players.get(3).hand)
                    .isNotEmpty()
                    .isNotNull()
                    .hasSize(12);
        });

    }
}
