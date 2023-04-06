package hwr.oop;

import java.util.*;


public class Game{

    int throwsPerRound = 2;
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


    protected void gameProcess() throws Exception {
        System.out.println("How many people will play the game?");
        numberOfPeople = gameScanner.nextInt();

        System.out.println("Who will play?");
        players = List.of(gameScanner.nextLine().split(" "));

        System.out.println("Would you like to start? Y/N");
        String answer = gameScanner.nextLine();
        if(answer.equals("Y")){
            System.out.println("Lets begin!");
        } else if (answer.equals("N")) {
            System.out.println("Press a key if you want to start");
        }else{
            throw new Exception("Invalid value");
        }

    }

    protected void bowlingRound(){

    }


}