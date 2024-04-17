package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_CARDS_PER_PLAYER = 12;

    public static void main(String[] args) {
        CardStack stack = new CardStack();


        List<Card> cardList = stack.getCardStack();
        System.out.println(cardList);

        List<List<Card>> players = new ArrayList<>();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players.add(new ArrayList<>());
        }

        for (int i = 0; i < NUM_CARDS_PER_PLAYER; i++) {
            for (List<Card> player : players) {
                if (!cardList.isEmpty()) {
                    int randomIndex = (int) (Math.random() * cardList.size());
                    Card card = cardList.remove(randomIndex);
                    player.add(card);
                }
            }
        }

        for (int i = 0; i < NUM_PLAYERS; i++) {
            List<String> hand = new ArrayList<>();
            for (Card card : players.get(i)) {
                hand.add(card.getName());
            }
            System.out.println("Player " + (i + 1) + "'s hand: " + hand);
            System.out.println("Player " + (i + 1) + "'s hand: " + players.get(i));
        }

    }
}



