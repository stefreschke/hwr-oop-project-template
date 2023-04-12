package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void calculateExtraPoints(List<Player> players){
        List<HashMap<Player, Integer>> strikeRounds = new ArrayList<>();
        List<HashMap<Player, Integer>> spareRounds = new ArrayList<>();
        List<HashMap<Player, Integer>> extraPointsList = new ArrayList<>();

        for(Player player: players){
            HashMap<Player, Integer> extraPoints = new HashMap<>();
            for (Round r: player.rounds){
                for(Throw th: r.throwing){
                    if(th.getState().equals(BowlingStates.STRIKE)){
                        extraPoints.put(player, r.roundNumber);
                        strikeRounds.add(extraPoints);
                    }
                    if(th.getState().equals(BowlingStates.SPARE)){
                        extraPoints.put(player, r.roundNumber);
                        spareRounds.add(extraPoints);
                    }
                }
            }
        }

        for(HashMap<Player, Integer> strikeRound: strikeRounds){
            for(Player player: strikeRound.keySet()){
                for (Round r: player.rounds) {

                }
            }
        }

    }
}


