package hwr.oop.cards;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Objects;

public class Topic {

    private final String name;
    private ArrayList<Card> cardList;

    public Topic(@JsonProperty("name") String name){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return Objects.equals(name, topic.name) &&
                Objects.equals(cardList, topic.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardList);
    }
}
