package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ExtraRoundPoints {
    public ExtraRoundPoints(Player player, Integer points) {
        this.player = player;
        this.points = points;
    }
    public Player player;
    public Integer points;

    public static List<HashMap<Player,List<Integer>>> getExtraRounds(List<Player> players) {
        List<HashMap<Player,List<Integer>>> extraRounds = new ArrayList<>();
        for (Player player:players){
            List<Integer> rounds = new ArrayList<>();
            for (Round r : player.rounds) {
                for (Throw th : r.throwing) {
                    if (th.getState().equals(BowlingStates.STRIKE)) {
                        rounds.add(r.roundNumber + 2);
                    } else if (th.getState().equals(BowlingStates.SPARE)) {
                        rounds.add(r.roundNumber + 1);
                    }
                }
            }
            HashMap<Player,List<Integer>> map = new HashMap<>();
            map.put(player,rounds);
            extraRounds.add(map);
        }

        return  extraRounds;
    }

    public static List<ExtraRoundPoints> getExtraPointsRounds(List<Player> players){
        List<ExtraRoundPoints> extraRoundPoints = new ArrayList<>();
        List<HashMap<Player,List<Integer>>> extraRounds= ExtraRoundPoints.getExtraRounds(players);
        for(HashMap<Player,List<Integer>> extraRound:extraRounds){
            for(Player player: extraRound.keySet()){
                for(List<Integer> rounds: extraRound.values()){
                    for(Integer roundNumber: rounds){
                        int points = Round.getPointsByRound(roundNumber, player.rounds);
                        extraRoundPoints.add(new ExtraRoundPoints(player, points));
                    }
                }
            }
        }
        return extraRoundPoints;
    }

}