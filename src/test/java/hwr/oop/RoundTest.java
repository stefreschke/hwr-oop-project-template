package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class RoundTest {
    private final int roundNumber = 1;
    private  final int points = 5;
    private  final int allPins = 10;
    private  final Round round = new Round(roundNumber, points);
    private  final Round strikeRound = new Round(roundNumber, allPins);
    private  final Round spareRound = new Round(roundNumber, allPins);
    private Player player = new Player("Anna",points, new ArrayList<>(), new ArrayList<>());


    @Test
    void newRoundTest() {
        Round testRound = new Round(roundNumber, points);
        Assertions.assertThat(testRound.getRoundNumber()).isEqualTo(round.getRoundNumber());
        Assertions.assertThat(testRound.getRoundPoints()).isEqualTo(round.getRoundPoints());
    }

    @Test
    void playNormalRoundTest() {
        final BowlingStates normalState = BowlingStates.NORMAL;
        final BowlingStates spareState = BowlingStates.SPARE;
        final BowlingStates strikeState = BowlingStates.STRIKE;

        final List<Integer> extraStrikeRounds = List.of(2,3);
        final List<Integer> extraSpareRounds = List.of(2, 3, 2);

        Round normalTestRound = round.playNormalRound(normalState, player);
        Assertions.assertThat(normalTestRound.getRoundNumber()).isEqualTo(round.getRoundNumber());
        Assertions.assertThat(normalTestRound.getRoundPoints()).isEqualTo(round.getRoundPoints());
        Assertions.assertThat(player.extraRounds).isNullOrEmpty();
        Assertions.assertThat(player.currentPoints).isEqualTo(5);

        Round strikeTestRound = round.playNormalRound(strikeState, player);
        Assertions.assertThat(strikeTestRound.getRoundNumber()).isEqualTo(strikeRound.getRoundNumber());
        Assertions.assertThat(strikeTestRound.getRoundPoints()).isEqualTo(strikeRound.getRoundPoints());
        Assertions.assertThat(player.extraRounds).containsExactlyInAnyOrderElementsOf(extraStrikeRounds);
        Assertions.assertThat(player.currentPoints).isEqualTo(points+allPins);

        Round spareTestRound = round.playNormalRound(spareState, player);
        Assertions.assertThat(spareTestRound.getRoundNumber()).isEqualTo(spareRound.getRoundNumber());
        Assertions.assertThat(spareTestRound.getRoundPoints()).isEqualTo(spareRound.getRoundPoints());
        Assertions.assertThat(player.extraRounds).containsExactlyInAnyOrderElementsOf(extraSpareRounds);
        Assertions.assertThat(player.currentPoints).isEqualTo(points+allPins+allPins);

    }

    @Test
    void playRoundTenTest() {
        final BowlingStates normalState = BowlingStates.NORMAL;
        final BowlingStates spareState = BowlingStates.SPARE;
        final BowlingStates strikeState = BowlingStates.STRIKE;

        final int lastRoundNumber = 10;


        Round normalTestLastRound = round.playRoundTen(normalState, points);
        Assertions.assertThat(normalTestLastRound.getRoundNumber()).isEqualTo(lastRoundNumber);
        Assertions.assertThat(normalTestLastRound.getRoundPoints()).isEqualTo(points);

        Round strikeTestLastRound = round.playRoundTen(strikeState, points);
        Assertions.assertThat(strikeTestLastRound.getRoundNumber()).isEqualTo(lastRoundNumber);
        Assertions.assertThat(strikeTestLastRound.getRoundPoints()).isEqualTo(10+points);

        Round spareTestLastRound = round.playRoundTen(spareState, points);
        Assertions.assertThat(spareTestLastRound .getRoundNumber()).isEqualTo(lastRoundNumber);
        Assertions.assertThat(spareTestLastRound.getRoundPoints()).isEqualTo(10+points);
    }
}
