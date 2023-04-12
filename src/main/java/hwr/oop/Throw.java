package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Throw implements BowlingPins {

    private Integer throwNumber;
    private Integer points;
    private BowlingStates state;

    public Throw(Integer throwNumber, Integer points, BowlingStates state) {
        this.throwNumber = throwNumber;
        this.points = points;
        this.state = state;
    }

    public static Throw throwingB(int throwNumber, int points) {
        if (throwNumber == 1) {
            if(points == pinsTotal){
                System.out.println("Strike!");
                pins.forEach((pin) -> {
                    pin = true;
                });
                return new Throw(1, 10, BowlingStates.STRIKE);
            }
            System.out.println((pinsTotal - points) + " pins left.");
        }
        else if (throwNumber == 2) {
            pins.forEach((pin) -> {
                pin = true;
            });
            if (points == pinsTotal) {
                System.out.println("Spare!");
                return new Throw(2, 10, BowlingStates.SPARE);
            }
        }
        return new Throw(throwNumber, points, BowlingStates.NORMAL);
    }
}
