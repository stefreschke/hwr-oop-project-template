package hwr.oop;

import java.util.ArrayList;
import java.util.List;



public class CardStack {

    CardGenerator cardGenerator = new CardGenerator();
    List<Card> cardStack = cardGenerator.generateAllCards();

    public List<Card> getCardStack() {
        return cardStack;
    }

    public List<Card> shuffleCardsStack(List<Card> cardStack) {
        List<Card> shuffledCards = new ArrayList<>();

        for (int i = 47; i >= 0; i--) {
            int r = (int) (Math.random() * i);
            Card tempCard = cardStack.remove(r);
            shuffledCards.add(tempCard);
        }

        return shuffledCards;
    }
}
