package hwr.oop;

import java.util.Random;

public class CardStack {

    private Card[] cardStack;

    public CardStack() {
        cardStack = this.generateStack();
    }

    public Card[] generateStack() {
        Card[] cardStack = new Card[]{
                new Card("9", CardColours.Trump, 0, "D9", 0)
                //TODO: Create every card
        };
        return cardStack;
    }

    public void shuffleStack() {
        Random random = new Random();
        for (int i = cardStack.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            Card temp = cardStack[index];
            cardStack[index] = cardStack[i];
            cardStack[i] = temp;
        }
    }


    public Card[] getCardStack() {
        return cardStack;
    }
}

