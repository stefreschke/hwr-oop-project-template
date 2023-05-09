package hwr.oop.cards;

import java.util.ArrayList;

public class Box {

    private ArrayList<Card> cardList;

    public Box(){
        cardList = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public ArrayList<Card> getCards() {

        return cardList;
    }
}
