package hwr.oop.Cards;

import java.util.ArrayList;

public class Box {

    private ArrayList<Card> cardList;

    public Box(){
        cardList = new ArrayList<Card>();
    }
    public int getCount() {
        return 5;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public Card getCardOfIndex(int i) {
        return cardList.get(i);
    }
}
