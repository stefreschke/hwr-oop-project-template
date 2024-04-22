package hwr.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_CARDS_PER_PLAYER = 12;

    public static List<Player> handOutCards(List<Player> playerList, List<Card> cardList) {
        playerList.get(0).setHand(cardList.subList(0, 12));
        playerList.get(1).setHand(cardList.subList(12, 24));
        playerList.get(2).setHand(cardList.subList(24, 36));
        playerList.get(3).setHand(cardList.subList(36, 48));
        return playerList;
    }

    public List<Player> createPlayers() {
        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player("Colin", 0);
        Player player2 = new Player("Chrissi", 0);
        Player player3 = new Player("Mihoshi", 0);
        Player player4 = new Player("Josh", 0);

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        return playerList;


    }


    public Player chooseStartPlayer(List<Player> playerList) {
        Random random = new Random();
        return playerList.get(random.nextInt(playerList.size()));
    }

    public static void main(String[] args) {
        Game game = new Game();
        CardStack stack = new CardStack();


        List<Card> cardList = stack.getCardStack();
        List<Player> players = game.createPlayers();

        List<Card> shuffledStack = stack.shuffleCardsStack(cardList);
        Player startPlayer = game.chooseStartPlayer(game.createPlayers());
        startPlayer.setScore(2);
        System.out.println(startPlayer.getScore());
        game.handOutCards(players, shuffledStack);




    }
}