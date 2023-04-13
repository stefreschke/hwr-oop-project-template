package hwr.oop;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class Player {
    int score = 0;
    int currentMultiplier = 0;
    String name = "Default Player Name";
    public List<Round> rounds;
    public List<Integer> extraRounds;

    public Player(String name, List<Round> rounds, List<Integer> extraRounds) {
        this.name = name;
        this.rounds = rounds;
        this.extraRounds = extraRounds;
    }
    public Player(String name) {
        this.name = name;
    }


    public int scoreAdd(int points) {
        if (points > -1) {
            score += points;
            return score;
        }
        throw new IllegalArgumentException("Added Points value must be 0 or higher.");
    }

    public List<Integer> addExtraRound(Integer th, int numberOfPins){
        if(th.equals(1) && numberOfPins == 10){
            extraRounds.add(th+1);
            extraRounds.add(th+2);
        }
        else if(th.equals(2) && numberOfPins == 10){
            extraRounds.add(th+1);
        }
        return extraRounds;
    }

}


