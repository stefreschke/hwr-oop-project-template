package hwr.oop.cards;

import java.util.ArrayList;

public class Topic {

    private final String name;
    private ArrayList<Card> cardList;

    public Topic(String name){
        this.name = name;
        cardList = new ArrayList<>();
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void createCard(String question, String answer){

        int id = cardList.size();

        Card newCard = new Card(question, answer, id);
        cardList.add(newCard);
    }

    public String getName() {
        return name;
    }
}
