package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Player {
    int score = 0;
    int currentMultiplier = 0;
    String name = "Default Player Name";
    public List<Round> rounds;
    public List<Integer> extraRounds;
    public Integer currentPoints;

    public Player(String name, Integer currentPoints, List<Round> rounds, List<Integer> extraRounds) {
        this.name = name;
        this.currentPoints = currentPoints;
        this.rounds = rounds;
        this.extraRounds = extraRounds;
    }

    public void calculatePlayerResults(Player player){
        for(Integer extraRound: player.extraRounds){
            player.currentPoints+=player.rounds.get(extraRound-1).roundPoints;;
        }
    }
}


