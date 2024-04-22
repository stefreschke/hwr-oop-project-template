package hwr.oop;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
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

    private String printPlayerNames(List<Player> playerList) {
        String playerNames = playerList.get(0).getName() + ", " + playerList.get(1).getName()
                + ", " + playerList.get(2).getName() + ", " + playerList.get(3).getName();
        return playerNames;
    }
    private String printFirstPlayer(List<Player> playerList) {
        int startPlayer = StartPlayer.getRandomNumber();
        String firstPlayer = "Der Startspieler ist: " + playerList.get(startPlayer).getName();
        return firstPlayer;
    }
    public List<Card> HandOutCards(List<Card> ShuffleCards){
        for (int i = 0; i < 4; i++) {
            List<Card> NewHand = ShuffleCards
            players.get(i).setHand();
        }
    }
    public static void main(String[] args) {
        CardStack stack = new CardStack();
        Game game = new Game();
        System.out.println(game.printPlayerNames(game.players));

        System.out.println(game.printFirstPlayer(game.players));

        List<Card> cardList = stack.getCardStack();

        List<Card> ShuffleCards = stack.ShuffleCardsStack(cardList);



        /*
        for (int i = 0; i < NUM_CARDS_PER_PLAYER; i++) {
            for (List<Card> player : players.size()) {
                if (!cardList.isEmpty()) {
                    int randomIndex = (int) (Math.random() * cardList.size());
                    Card card = cardList.remove(randomIndex);
                    player.add(card);
                }
            }
        }
         */
    }
}



