package hwr.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CardStack {

    CardGenerator cardGenerator = new CardGenerator();
    List<Card> cardStack = cardGenerator.generateAllCards();

    public List<Card> getCardStack() {
        return cardStack;
    }

    public List<Card> ShuffleCardsStack(List<Card> CardStack) {
        List<Card> ShuffledCards = new ArrayList<Card>();

        for (int i = 47; i >= 0; i--) {
            int r = (int) (Math.random() * i);
            Card tempCard = CardStack.remove(r);
            ShuffledCards.add(tempCard);
        }

        return ShuffledCards;
    }
}

    public List<Card> HandOutCards()  {

    }