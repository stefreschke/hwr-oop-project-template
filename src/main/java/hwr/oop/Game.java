package hwr.oop;

import java.awt.event.KeyEvent;
import java.util.*;



public class Game {
    List<Pin> pins = List.of(new Pin(1, true), new Pin(2, true), new Pin(3, true),
            new Pin(4, true), new Pin(5, true), new Pin(6, true),
            new Pin(7, true), new Pin(8, true), new Pin(9, true),
            new Pin(10, true));

    List<Round> rounds;

    public final int pinsTotal = 10;
    public final int lastRound = 10;

    enum BowlingStates {
        STRIKE,
        SPARE,
        NORMAL
    }

    Scanner gameScanner = new Scanner(System.in);
    Integer numberOfPeople;
    List<Player> players;

    protected void gameProcess(KeyEvent e) throws Exception {
        System.out.println("How many people will play the game?");
        numberOfPeople = gameScanner.nextInt();

        System.out.println("Who will play?");
        List.of(gameScanner.nextLine().split(" ")).
                forEach((name) -> players.add(new Player(name, 0)));

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
            }else {
                System.out.println("Type a valid character.");
            }
        }
    }

    protected void bowlingRound() {
        for (int round = 1; round <= 9; round++) {
            for (Player player : players) {
                for (int throwsPerRound = 2; throwsPerRound > 0; throwsPerRound--) {
                    System.out.println(player.name + " " + round + " : \n" + "Which pins did you hit in the first throw ?");
                    String pinsHit = gameScanner.nextLine();
                    List.of(pinsHit.split(" ")).forEach((number) ->{
                        if (Integer.parseInt(number) == pins.get(Integer.parseInt(number) - 1).pinNumber) {
                            pins.get(Integer.parseInt(number) - 1).standing = false;
                        }
                    });
                    int countPinsFirst = (int) pins.stream().filter(pin -> !pin.standing).count();

                    if (countPinsFirst == pinsTotal) {
                        System.out.println("Strike!");
                        player.score = player.scoreAdd(countPinsFirst);
                        rounds.add(new Round(round, player, countPinsFirst, BowlingStates.STRIKE));
                        break;
                    }else{
                        System.out.println("Pins left: " + (pinsTotal - countPinsFirst) + "\n Which pins got hit in the second throw?");
                        List.of(pinsHit.split(" ")).forEach((number) ->{
                            if (Integer.parseInt(number) == pins.get(Integer.parseInt(number) - 1).pinNumber) {
                                pins.get(Integer.parseInt(number) - 1).standing = false;
                            }
                        });
                        int countPinsSecond = (int) pins.stream().filter(pin -> !pin.standing).count();
                        if ((pinsTotal - countPinsFirst) == countPinsSecond) {
                            System.out.println("Spare!");
                            player.score = player.scoreAdd(10);
                            rounds.add(new Round(round, player, 10, BowlingStates.SPARE));
                        } else {
                            int roundPoints = pinsTotal - countPinsFirst - countPinsSecond;
                            System.out.println(roundPoints+ " pins left.");
                            player.score = player.scoreAdd(roundPoints);
                            rounds.add(new Round(round, player, roundPoints, BowlingStates.NORMAL));
                        }
                    }
                }
            }
        }
        rounds = extraPoints(rounds);
        for (Player player : players) {
            System.out.println(player.name + " " + 10 + " : \n" + "Which pins did you hit in the first throw ?");
            String pinsHit = gameScanner.nextLine();
            List.of(pinsHit.split(" ")).forEach((number) -> {
                if (Integer.parseInt(number) == pins.get(Integer.parseInt(number) - 1).pinNumber) {
                    pins.get(Integer.parseInt(number) - 1).standing = false;
                }
            });
            int countPinsFirst = (int) pins.stream().filter(pin -> !pin.standing).count();

            if (countPinsFirst == pinsTotal) {
                System.out.println("Strike!  \n"+ "You get two additional shots.");
                player.score = player.scoreAdd(countPinsFirst);
                rounds.add(new Round(lastRound, player, countPinsFirst, BowlingStates.STRIKE));
                for(int extraRound = 1; extraRound<=2; extraRound++){
                    System.out.println(player.name + " " + 10 + " : \n" + "Which pins did you hit in the first throw ?");
                    pinsHit = gameScanner.nextLine();
                    List.of(pinsHit.split(" ")).forEach((number) -> {
                        if (Integer.parseInt(number) == pins.get(Integer.parseInt(number) - 1).pinNumber) {
                            pins.get(Integer.parseInt(number) - 1).standing = false;
                        }
                    });
                     int extraCountPins = (int) pins.stream().filter(pin -> !pin.standing).count();
                     if(extraCountPins == pinsTotal){
                         System.out.println("Strike!");
                         player.score = player.scoreAdd(extraCountPins);
                         rounds.add(new Round(lastRound+extraRound, player, extraCountPins, BowlingStates.STRIKE));
                     }else{
                         System.out.println("You hit "+ extraCountPins+ " pins.");
                         player.score = player.scoreAdd(extraCountPins);
                         rounds.add(new Round(lastRound+extraRound, player, extraCountPins, BowlingStates.NORMAL));
                     }
                }
            } else {
                System.out.println("Pins left: " + (pinsTotal - countPinsFirst) + "\n Which pins got hit in the second throw?");
                List.of(pinsHit.split(" ")).forEach((number) -> {
                    if (Integer.parseInt(number) == pins.get(Integer.parseInt(number) - 1).pinNumber) {
                        pins.get(Integer.parseInt(number) - 1).standing = false;
                    }
                });
                int countPinsSecond = (int) pins.stream().filter(pin -> !pin.standing).count();
                if ((pinsTotal - countPinsFirst) == countPinsSecond) {
                    System.out.println("Spare!  \n"+ "You get one additional shot.");
                    player.score = player.scoreAdd(10);
                    rounds.add(new Round(lastRound, player, 10, BowlingStates.SPARE));

                    System.out.println(player.name + " " + 10 + " : \n" + "Which pins did you hit in the first throw ?");
                    pinsHit = gameScanner.nextLine();
                    List.of(pinsHit.split(" ")).forEach((number) -> {
                        if (Integer.parseInt(number) == pins.get(Integer.parseInt(number) - 1).pinNumber) {
                            pins.get(Integer.parseInt(number) - 1).standing = false;
                        }
                    });
                    int extraCountPins = (int) pins.stream().filter(pin -> !pin.standing).count();
                    if(extraCountPins == pinsTotal){
                        System.out.println("Strike!");
                        player.score = player.scoreAdd(extraCountPins);
                        rounds.add(new Round(lastRound+1, player, extraCountPins, BowlingStates.STRIKE));
                    }else{
                        System.out.println("You hit "+ extraCountPins+ " pins.");
                        player.score = player.scoreAdd(extraCountPins);
                        rounds.add(new Round(lastRound+1, player, extraCountPins, BowlingStates.NORMAL));
                    }
                }else {
                    int roundPoints = pinsTotal - countPinsFirst - countPinsSecond;
                    System.out.println(roundPoints + " pins left.");
                    player.score = player.scoreAdd(roundPoints);
                    rounds.add(new Round(lastRound, player, roundPoints, BowlingStates.NORMAL));
                }
            }
        }

        int max = 0;
        for(Player player: players){
            if(player.score > max){
                max = player.score;
            }
        }
        List<String> winnerNames = new ArrayList<>();
        for(Player player: players){
            if(player.score ==max){
                winnerNames.add(player.name) ;
            }
        }

        if(winnerNames.size() > 1){
            System.out.println("Game over! \n"+ "It is a draw!\n" + "The winners are :"+ winnerNames+ " with "+ max+ " points.");
        }else{
            System.out.println("Game over! \n"+ "The winner is: " + winnerNames.get(0)+ " with "+ max+ " points.");
        }

    }
    public List<Round> extraPoints (List<Round> rounds){
        for(Round round:rounds){
            if(round.state.equals(BowlingStates.STRIKE)){
                Player p = round.player;
                Integer r = round.round;

                int times = 0;
                for(Round round1:rounds){
                    if(round1.player.equals(p)){
                        if(round1.round-1 == r || round1.round-2 == r ){
                            p.score = p.scoreAdd(round1.points);
                            times += 1;
                        }
                    }if(times == 2){break;}
                }
            }
            else if(round.state.equals(BowlingStates.SPARE)){
                Player p = round.player;
                Integer r = round.round;

                int times = 0;
                for(Round round1:rounds){
                    if(round1.player.equals(p)){
                        if(round1.round-1 == r){
                            p.score = p.scoreAdd(round1.points);
                            times += 1;
                        }
                    }if(times == 1){break;}
                }
            }
        }
        return rounds;
    }



}

