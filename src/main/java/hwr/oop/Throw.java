package hwr.oop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Throw {

    private Integer throwNumber;
    private Integer points;
    private BowlingStates state;
    private final int pinsTotal = 10;

    public Throw(Integer throwNumber, Integer points, BowlingStates state) {
        this.throwNumber = throwNumber;
        this.points = points;
        this.state = state;
    }
}
