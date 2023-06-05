package hwr.oop.cards;

import java.util.ArrayList;

public interface BoxInterface{
    public void addCard(Card card);
    public void moveCardUp(Card card);
    public void moveCardDown(Card card);
    public boolean isEmpty_learned();
    public boolean isEmpty_unlearned();
    public void updateBox();
    public Card getRandomCard();
    public ArrayList<Card> getLearnedCardList();
    public ArrayList<Card> getUnlearnedCardList();
    public boolean equals(Object o);

}