package hwr.oop.doppelkopf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class CardStack {

    CardGenerator cardGenerator = new CardGenerator();
    List<Card> cardStack = cardGenerator.generateAllCards();

    public List<Card> getCardStack() {
        return cardStack;
    }

    public List<Card> shuffleCardsStack(List<Card> cardStack) {
        List<Card> shuffledCards = new ArrayList<>(cardStack);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
        }
    }
