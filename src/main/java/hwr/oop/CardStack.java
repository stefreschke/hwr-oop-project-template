package hwr.oop;

import java.util.Random;

public class CardStack {

    private String[] cardStack;

    public CardStack() {

        cardStack = Cards.generateCards();

    }

    public void shuffleStack() {
        Random random = new Random();
        for (int i = cardStack.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            String temp = cardStack[index];
            cardStack[index] = cardStack[i];
            cardStack[i] = temp;
        }
    }


    public String[] getCardStack() {
        return cardStack;
    }
}

