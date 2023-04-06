package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExtraRoundPoints {
    public ExtraRoundPoints(Player player, Game.BowlingStates state, List<Integer> rounds) {
        this.player = player;
        this.state = state;
        this.rounds = rounds;
    }
    public Player player;
    public Game.BowlingStates state;
    public List<Integer> rounds;
}