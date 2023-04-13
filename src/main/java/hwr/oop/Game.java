package hwr.oop;

import java.awt.event.KeyEvent;
import java.util.*;

import static hwr.oop.Round.*;


public class Game {
    Scanner gameScanner = new Scanner(System.in);
    List<Player> players = new ArrayList<>();
    List<GameResult> gameResults = new ArrayList<>();

    List<Integer> extraRounds = new ArrayList<>();
    int numberOfPeople;
    public static Throw th;


    protected void gameProcess(KeyEvent e){
        System.out.println("How many people will play the game?");
        numberOfPeople = gameScanner.nextInt();

        List<String> playersNames = new ArrayList<>();
        System.out.println("Who will play?");

        String name;
        for(int i = 0; i<numberOfPeople; i++){
            System.out.println("Player number "+ (i+1));
            name = gameScanner.nextLine();
            playersNames.add(name);
        }
        System.out.println(playersNames);

        for(String playerName: playersNames){
            List<Round> playerRound = new ArrayList<>();
            players.add(new Player(playerName, playerRound, extraRounds));
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
                Round currentRound = normalRound(roundNumber+1);
                player.rounds.add(currentRound);
                player.extraRounds = pl.getExtraRounds();
            }
        }
        for(Player player : players){
            System.out.println(player.name+ " is playing.");
            Round lastRound = roundTen();
            player.rounds.add(lastRound);
        }
        gameResults = calculateResults(players);
        GameResult.sortResults(gameResults);
        for(GameResult gameResult: gameResults){
            System.out.println(gameResult.player.name+ " "+ gameResult.endPoints);
        }
        System.out.println("The winner is "+ gameResults.get(0).player.name + " with "+ gameResults.get(0).player.getScore() + " points. Congratulation!");
    }


    public List<GameResult> calculateResults(List<Player> players){
        getExtraPointsRounds(players);
        for(Player player: players){
            int totalPoints = 0;
            for(Round round: player.rounds){
                for(Throw th : round.throwing){
                    totalPoints = totalPoints + th.getPoints();
                }
            }
            gameResults.add(new GameResult(totalPoints, player));
        }
        return gameResults;
    }
    public int getNumberOfPeople(){
        return  numberOfPeople;
    }

    public static void main(String[] args){
        Game game = new Game();
        KeyEvent e = null;
        game.gameProcess(e);
    }
    
}

