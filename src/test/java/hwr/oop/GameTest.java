package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    private  final List<Boolean> pinsH =List.of(true,true,false,true,false,false,true,true,true,true);
    private final String playerNames = "Anna Annika Friedrich";

    private final BowlingStates strikeState = BowlingStates.STRIKE;
    private final Game game = new Game();

    @Test
    void setStateTest() {
        int th = 1;
        BowlingStates state = game.setState(th, 10);
        Assertions.assertThat(state).isEqualTo(strikeState);
    }
    @Test
    void convertArrayTest() {
        String pinsHit = "3 5 6";
        List<Boolean> pins = game.convertArray(pinsHit);
        Assertions.assertThat(pins).containsExactlyInAnyOrderElementsOf(pinsH);
    }
    @Test
    void countPointsTest() {
        Integer p = game.countPoints(pinsH);
        Assertions.assertThat(p).isEqualTo(3);
    }
    @Test
    void createPlayersTest() {
        List<Player> players= List.of(new Player("Anna",0, new ArrayList<Round>(),new ArrayList<Integer>()),
                new Player("Annika",0, new ArrayList<Round>(),new ArrayList<Integer>()),
                new Player("Friedrich",0, new ArrayList<Round>(), new ArrayList<Integer>()));
        List<Player> testPlayers = game.createPlayers(playerNames);
        for(int i  = 0;i< testPlayers.size(); i++ ){
            Assertions.assertThat(testPlayers.get(i).name).isEqualTo(players.get(i).name);
            Assertions.assertThat(testPlayers.get(i).currentPoints).isEqualTo(players.get(i).currentPoints);
            Assertions.assertThat(testPlayers.get(i).rounds).isNullOrEmpty();
            Assertions.assertThat(testPlayers.get(i).extraRounds).isNullOrEmpty();
        }

    }
    @Test
    void getWinnerTest() {
        List<Player> players= List.of(new Player("Anna",10, new ArrayList<Round>(),new ArrayList<Integer>()),
                new Player("Annika",50, new ArrayList<Round>(),new ArrayList<Integer>()),
                new Player("Friedrich",80, new ArrayList<Round>(), new ArrayList<Integer>()));
        Player winner = players.get(0);
        Player player = game.getWinner(players);
        Assertions.assertThat(player.name).isEqualTo(winner.name);
        Assertions.assertThat(player.currentPoints).isEqualTo(winner.currentPoints);
    }
}
