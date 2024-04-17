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
}

