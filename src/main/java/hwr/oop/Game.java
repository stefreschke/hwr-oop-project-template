package hwr.oop;

import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Game {
    Scanner gameScanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();



    protected void gameProcess(KeyEvent e) throws Exception {
        System.out.println("How many people will play the game?");
        int numberOfPeople = gameScanner.nextInt();

        List<String> playersNames = new ArrayList<>();
        System.out.println("Who will play?");


        String name = gameScanner.nextLine();
        for(int i = 0; i<numberOfPeople; i++){
            System.out.println("Player number "+ (i+1));
            name = gameScanner.nextLine();
            playersNames.add(name);
        }
        System.out.println(playersNames);

        for(String playerName: playersNames){
            List<Round> playerRound = new ArrayList<>();
            players.add(new Player(playerName, playerRound));
        }


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

        for(int roundNumber = 0; roundNumber< 8; roundNumber++){
            for(Player player : players){
                System.out.println(player.name+ " is playing.");
                Round currentRound = Round.normalRound(roundNumber+1);
                player.rounds.add(currentRound);
            }
        }
        for(Player player : players){
            System.out.println(player.name+ " is playing.");
            Round lastRound = Round.roundTen();
            player.rounds.add(lastRound);
        }
    }


    public List<GameResults> calculateResults(List<Player> players){
        List<GameResults> gameResults = new ArrayList<>();
        for(Player player: players){
            int totalPoints = 0;
            for(Round round: player.rounds){
                for(Throw th : round.throwing){
                    totalPoints = totalPoints + th.getPoints();
                }
            }
            gameResults.add(new GameResults(totalPoints, player));
        }
        List<ExtraRoundPoints> extraRoundPoints = ExtraRoundPoints.getExtraPointsRounds(players);
        for(ExtraRoundPoints extraRoundPoint: extraRoundPoints){
            gameResults.add(new GameResults(extraRoundPoint.getPoints(), extraRoundPoint.player));
        }

        return gameResults;
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        KeyEvent e = null;
        game.gameProcess(e);
    }
    
}

