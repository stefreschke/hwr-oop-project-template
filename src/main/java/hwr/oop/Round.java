package hwr.oop;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static hwr.oop.Game.th;

@Getter
@Setter
public class Round implements BowlingPins{


    public Integer roundNumber;

    public List<Throw> throwing;
    public Integer  roundPoints;
    public Round(Integer roundNumber, List<Throw> throwing) {
        this.roundNumber = roundNumber;
        this.throwing = throwing;
    }

    public Throw throwBall;

    static final Scanner gameScanner = new Scanner(System.in);

    public static Round normalRound(int roundNumber){

        List<Throw> throwing = new ArrayList<>();

        System.out.println("Round "+ roundNumber+ "\n Which pins did you hit in the first throw ?");
        List<String> pinsHit = List.of(gameScanner.nextLine().split(" "));

        hittedPins(pinsHit);

        throwing.add(Throw.throwingB(1,countHittedPins()));

        if(countHittedPins() == pinsTotal) {
            return new Round(roundNumber,throwing);
        }
        int pinsLeft = pinsTotal-countHittedPins();
        System.out.println("Pins left: " + (pinsLeft  + "\n Which pins got hit in the second throw?"));
        pinsHit = List.of(gameScanner.nextLine().split(" "));
        hittedPins(pinsHit);
        throwing.add(Throw.throwingB(2,countHittedPins()));


        return new Round(roundNumber,throwing );
    }

    public static Round roundTen(){
        List<Throw> throwing = new ArrayList<>();
        System.out.println("Last round!");

        System.out.println("Which pins did you hit in the first throw ?");

        List<String> pinsHit = List.of(gameScanner.nextLine().split(" "));

        hittedPins(pinsHit);
        throwing.add(Throw.throwingB(1,countHittedPins()));
        if(countHittedPins() == pinsTotal) {

            System.out.println("\n You have two additional throws."+"\n First throw.");

            pinsHit = List.of(gameScanner.nextLine().split(" "));
            hittedPins(pinsHit);
            throwing.add(Throw.throwingB(1,countHittedPins()));

            System.out.println("Second throw.");
            pinsHit = List.of(gameScanner.nextLine().split(" "));
            hittedPins(pinsHit);
            throwing.add(Throw.throwingB(2,countHittedPins()));
            return new Round(10,throwing);
            
        }else{
            int pinsLeft = pinsTotal-countHittedPins();
            System.out.println("Pins left: " + (pinsLeft  + "\n Which pins got hit in the second throw?"));
            pinsHit = List.of(gameScanner.nextLine().split(" "));
            hittedPins(pinsHit);

            if (pinsTotal==countHittedPins()){
                throwing.add(Throw.throwingB(2,countHittedPins()));
                System.out.println("\n You have one additional throw.");

                pinsHit = List.of(gameScanner.nextLine().split(" "));
                hittedPins(pinsHit);
                throwing.add(Throw.throwingB(1,countHittedPins()));
            }

            throwing.add(Throw.throwingB(2,countHittedPins()));
            System.out.println(pinsLeft+ " pins left.");
        }
        return new Round(10, throwing);
    }

    private static void hittedPins(List<String> pinsHit){
        boolean valid = true;
        for(String pin: pinsHit){
            int hitPin =Integer.parseInt(pin) ;
            if(hitPin < 0 || hitPin > 10){
                System.out.println("Type a valid number.");
                valid = false;
                break;
            }
            if(pins.get(hitPin).equals(false)){
                System.out.println("Pin already got hit. Type a valid pin.");
                valid = false;
                break;
            }
            pins.set(Integer.parseInt(pin)-1,false);
        }
        if(!valid){
            gameScanner.nextLine();
        }
    }
    private static int countHittedPins(){
        return Collections.frequency(BowlingPins.pins,false) ;
    }

    public static int getPointsByRound(int roundNumber, List<Round> rounds) {
        int points = 0;
        List<Throw> throwing= rounds.get(roundNumber).getThrowing();
        for (Throw th : throwing) {
            points+= th.getPoints();
        }
        return points;
    }
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

    public static List<Player> getExtraPointsRounds(List<Player> players){
        List<HashMap<Player,List<Integer>>> extraRounds= Round.getExtraRounds(players);
        for(HashMap<Player,List<Integer>> extraRound:extraRounds){
            for(Player player: extraRound.keySet()){
                for(List<Integer> rounds: extraRound.values()){
                    for(Integer roundNumber: rounds){
                        th.setPoints(Round.getPointsByRound(roundNumber, player.rounds)+th.getPoints());
                        players.add(new Player(player.name,player.rounds));
                    }
                }
            }
        }
        return players;
    }
}



