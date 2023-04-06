package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Round {
    public Round(Integer round,Player player, Integer points,  Game.BowlingStates state) {
        this.round = round;
        this.player = player;
        this.state = state;
    }
    public Integer round;
    public Player player;

    public Integer points;
    public Game.BowlingStates state;



}