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

    public Round playRound(BowlingStates state, Player player){
        Round currentRound;
        playedState= state;
        if (state.equals(BowlingStates.STRIKE)) {
            System.out.println("Strike! " + 10 + " points added");
            player.extraRounds.add(roundNumber + 1);
            player.extraRounds.add(roundNumber + 2);
            player.currentPoints += 10;
            currentRound = new Round(roundNumber, 10);
        } else if(state.equals(BowlingStates.SPARE)){
            System.out.println("Spare! " + 10 + " points added");
            player.extraRounds.add(roundNumber + 1);
            currentRound = new Round(roundNumber, 10);
            player.currentPoints += 10;
        }
        else{
            currentRound = new Round(roundNumber, player.currentPoints);
        }
        return currentRound;
    }
}



