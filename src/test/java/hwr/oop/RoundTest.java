package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



public class RoundTest {
    private final int roundNumber = 1;
    private  final int points = 5;
    private  final int allPins = 10;
    private  final Round round = new Round(roundNumber, points);
    private  final Round strikeRound = new Round(roundNumber, allPins);
    private  final Round spareRound = new Round(roundNumber, allPins);

    @Test
    void newRoundTest() {
        Round testRound = new Round(roundNumber, points);
        Assertions.assertThat(testRound.getRoundNumber()).isEqualTo(round.getRoundNumber());
        Assertions.assertThat(testRound.getRoundPoints()).isEqualTo(round.getRoundPoints());
    }

    @Test
    void normalRoundTest() {
        final int firstThrow = 1;
        Round normalTestRound = round.playRound(points, firstThrow);
        Assertions.assertThat(normalTestRound.getRoundNumber()).isEqualTo(round.getRoundNumber());
        Assertions.assertThat(normalTestRound.getRoundPoints()).isEqualTo(round.getRoundPoints());

        Round strikeTestRound = round.playRound(allPins, firstThrow);
        Assertions.assertThat(strikeTestRound.getRoundNumber()).isEqualTo(strikeRound.getRoundNumber());
        Assertions.assertThat(strikeTestRound.getRoundPoints()).isEqualTo(strikeRound.getRoundPoints());

        final int secondThrow = 2;
        Round spareTestRound = round.playRound(allPins, secondThrow);
        Assertions.assertThat(spareTestRound.getRoundNumber()).isEqualTo(spareRound.getRoundNumber());
        Assertions.assertThat(strikeTestRound.getRoundPoints()).isEqualTo(spareRound.getRoundPoints());
    }
}
