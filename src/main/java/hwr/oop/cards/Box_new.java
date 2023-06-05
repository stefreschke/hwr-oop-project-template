import hwr.oop.cards.BoxInterface;
import hwr.oop.cards.Card;

import java.util.ArrayList;

public class Box_new implements BoxInterface{

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void moveCardUp(Card card) {

    }

    @Override
    public void moveCardDown(Card card) {

    }

    @Override
    public boolean isEmpty_learned() {
        return false;
    }

    @Override
    public boolean isEmpty_unlearned() {
        return false;
    }

    @Override
    public void updateBox() {

    }

    @Override
    public Card getRandomCard() {
        return null;
    }

    @Override
    public ArrayList<Card> getLearnedCardList() {
        return null;
    }

    @Override
    public ArrayList<Card> getUnlearnedCardList() {
        return null;
    }
}