package hwr.oop;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {
    private final String name = "Anna";
    private final Integer currentPoints = 29;
    private final Integer finalPoints = 66;
    private final List<Integer> extraRounds = List.of(1, 2, 3,7);
    private final List<Round> rounds = List.of(new Round(1, 10),
            new Round(2, 7),new Round(3, 10),
            new Round(4, 9),new Round(5, 8),
            new Round(6, 10),new Round(7, 10),
            new Round(8, 2),new Round(9, 9),
            new Round(10 ,6));

    private Player player = new Player(name, currentPoints, rounds, extraRounds);

    @Test
    void newPlayerTest() {
        List<Round> testRounds = List.of(new Round(1, 10));
        List<Integer> testExtraRounds = List.of(1, 2, 3 ,4);
        Player testPlayer = new Player("Anna", 23,testRounds,testExtraRounds );
        Assertions.assertThat(testPlayer.name).isEqualTo(player.name);
        Assertions.assertThat(testPlayer.currentPoints).isEqualTo(player.currentPoints);
        Assertions.assertThat(testPlayer.extraRounds).containsExactlyInAnyOrderElementsOf(player.extraRounds);
        Assertions.assertThat(testPlayer.rounds).filteredOn(
                r -> r.getRoundNumber().equals(rounds.get(0).getRoundNumber())
                        && r.roundPoints.equals(rounds.get(0).roundPoints));
    }
    @Test
    void calculatePlayerResultsTest() {
        player.calculatePlayerResults(player);
        Assertions.assertThat(player.currentPoints).isEqualTo(finalPoints);
    }
}
