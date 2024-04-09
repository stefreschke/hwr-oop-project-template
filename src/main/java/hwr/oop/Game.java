package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_CARDS_PER_PLAYER = 12;

    public static void main(String[] args) {
        CardStack stack = new CardStack();
        stack.shuffleStack();
        String[] cardStack = stack.getCardStack();

        List<String> cardList = new ArrayList<>(List.of(cardStack));

        List<List<String>> players = new ArrayList<>();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players.add(new ArrayList<>());
        }

        for (int i = 0; i < NUM_CARDS_PER_PLAYER; i++) {
            for (List<String> player : players) {
                if (!cardList.isEmpty()) {
                    int randomIndex = (int) (Math.random() * cardList.size());
                    String card = cardList.remove(randomIndex);
                    player.add(card);
                }
            }
        }

        for (int i = 0; i < NUM_PLAYERS; i++) {
            System.out.println("Player " + (i + 1) + "'s hand: " + players.get(i));
        }
    }
}


