package hwr.oop;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class Round {

    private Integer roundNumber;
    private BowlingStates state;
    public  Integer roundPoints;
    private final int pinsTotal = 10;
    private BowlingStates initial = BowlingStates.NORMAL;
    private Game game = new Game();

    public Round(Integer roundNumber,Integer roundPoints) {
        this.roundNumber = roundNumber;
        this.roundPoints = roundPoints;
    }

    public BowlingStates getPlayedState() {
        return playedState;
    }

    BowlingStates playedState;
    public static Player pl;
    static final Scanner gameScanner = new Scanner(System.in);
    public static List<Integer> extraRounds = new ArrayList<>();

    public Round playRound(int points, int th){
        playedState = game.setState(th, points);
        Round currentRound;
        if (playedState.equals(BowlingStates.STRIKE)) {
            currentRound = new Round(roundNumber, 10);
        } else {
            roundPoints += points;
            playedState = game.setState(th,(pinsTotal-roundPoints));
            currentRound = new Round(roundNumber, roundPoints);
        }
        return currentRound;
    }
}



