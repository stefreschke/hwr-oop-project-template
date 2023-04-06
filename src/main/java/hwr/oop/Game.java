package hwr.oop;

import java.awt.event.KeyEvent;
import java.util.*;



public class Game {


    public final int pinsTotal = 10;

    enum BowlingStates {
        STRIKE(10),
        SPARE(10);
        private final java.lang.Integer stateValue;

        BowlingStates(Integer stateValue) {
            this.stateValue = stateValue;
        }

        public static BowlingStates valueOfState(String value) {
            for (BowlingStates state : values()) {
                if (state.stateValue.equals(value)) {
                    return state;
                }
            }
            return null;
        }

    }

    Scanner gameScanner = new Scanner(System.in);
    Integer numberOfPeople;
    List<String> players;


    protected void gameProcess(KeyEvent e) throws Exception {
        System.out.println("How many people will play the game?");
        numberOfPeople = gameScanner.nextInt();

        System.out.println("Who will play?");
        players = List.of(gameScanner.nextLine().split(" "));

        System.out.println("Would you like to start? Y/N");
        while (true) {
            String answer = gameScanner.nextLine();

            if (answer.equals("Y")) {
                System.out.println("Lets begin!");
                break;
            } else if (answer.equals("N")) {
                System.out.println("Press 'enter' if you want to start.");
                while (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        System.out.println("Let's begin!");
                        break;
                    }
                }
            } else {
                System.out.println("Type a valid character.");
            }
        }


    }

    protected void bowlingRound() {
        for (int round = 1; round <= 10; round++) {
            for (String player : players) {
                for (int throwsPerRound = 2; throwsPerRound > 0; throwsPerRound--) {
                    System.out.println(player + " " + round + " : \n" + "How many pins did they hit in throw " + throwsPerRound);
                    int pinsHitFirst = gameScanner.nextInt();
                    if (pinsHitFirst == 10) {
                        System.out.println("Strike!");
                        break;
                    }else{
                        System.out.println("Pins left: " + (pinsTotal - pinsHitFirst) + "\n How many pins got hit in the second throw?");
                        int pinsHitSecond = gameScanner.nextInt();
                        if ((pinsTotal - pinsHitFirst) == pinsHitSecond) {
                            System.out.println("Spare!");
                        } else {
                            System.out.println((pinsTotal - pinsHitFirst - pinsHitSecond) + " pins left.");
                        }
                    }
                }
            }
        }
    }
}