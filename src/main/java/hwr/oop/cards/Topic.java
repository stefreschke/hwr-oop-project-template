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
        if (cardList.size() != topic.cardList.size()) {
            return false;
        }
        for (int current = 0; current < cardList.size(); current++) {
            boolean notequal = !cardList.get(current).equals(topic.cardList.get(current));
            if (notequal) {
                return false;
            }
        }
        return Objects.equals(name, topic.name);
    }
}
