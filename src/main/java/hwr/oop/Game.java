package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_CARDS_PER_PLAYER = 12;

    private List<Player> createPlayers() {
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
    List<Player> players = this.createPlayers();


    public static void main(String[] args) {
        CardStack stack = new CardStack();


        List<Card> cardList = stack.getCardStack();

        List<Card> shuffledStack = stack.ShuffleCardsStack(cardList);
    }
}



