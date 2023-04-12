package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Getter
@Setter
public class Player {
    int score = 0;
    int currentMultiplier = 0;
    String name = "Default Player Name";
    public List<Round> rounds;

    public Player(String name, List<Round> rounds) {
        this.name = name;
        this.rounds = rounds;
    }
    public Player(String name) {
        this.name = name;
    }

    public List<Round> addRound(Round addedRound){
        rounds.add(addedRound);
        return rounds;
    }

    public int scoreAdd(int points) {
        if (points > -1) {
            score += points;
            return score;
        }
        throw new IllegalArgumentException("Added Points value must be 0 or higher.");
    }

}


