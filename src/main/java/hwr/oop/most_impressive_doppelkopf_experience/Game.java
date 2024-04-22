package hwr.oop.most_impressive_doppelkopf_experience;

import java.util.List;

public class Game {

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_CARDS_PER_PLAYER = 12;
    List<Player> players = this.createPlayers();
    CardStack stack = new CardStack();
    List<Card> cardList = stack.getCardStack();
    List<Card> shuffledStack = stack.shuffleCardsStack(cardList);

    public List<Player> handOutCards() {
        players.get(0).setHand(cardList.subList(0, 12));
        players.get(1).setHand(cardList.subList(12, 24));
        players.get(2).setHand(cardList.subList(24, 36));
        players.get(3).setHand(cardList.subList(36, 48));
        return players;
    }

    public List<Player> createPlayers() {
        Player player1 = new Player("Colin", 0);
        Player player2 = new Player("Chrissi", 0);
        Player player3 = new Player("Mihoshi", 0);
        Player player4 = new Player("Josh", 0);

        return List.of(player1, player2, player3, player4);

    }

    public Card playCard() {
return null;
    }



    public static void main(String[] args) {
        Game game = new Game();
        game.handOutCards();




    }
}