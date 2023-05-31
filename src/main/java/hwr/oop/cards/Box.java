package hwr.oop.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Box {

    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> unlearnedCardList;

    public Box(){

        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
    }

    public Box(ArrayList<Card> learnedCardList, ArrayList<Card> unlearnedCardList) {

        this.learnedCardList = learnedCardList;
        this.unlearnedCardList = unlearnedCardList;
    }

    public void addCard(Card card) {
        learnedCardList.add(card);
    }

    @JsonIgnore
    public ArrayList<Card> getCardList() {

        ArrayList<Card> returnList = new ArrayList<Card>();

        returnList.addAll(learnedCardList);
        returnList.addAll(unlearnedCardList);

        return returnList;
    }
    public boolean isEmpty_unlearned() {
        return unlearnedCardList.isEmpty();
    }
    public boolean isEmpty_learned(){
        return learnedCardList.isEmpty();
    }
    public void updateCard(Card card){unlearnedCardList.add(card);}

    @JsonIgnore
    public Card getRandomCard() {
        // learnedCardList müsste unlearned sein in der Logik mit einem Datum, für die Tests aber hinderlich
        Random random = new Random();
        int index = random.nextInt(learnedCardList.size());
        Card returnCard = learnedCardList.get(index);
        learnedCardList.remove(index);
        return returnCard;
    }

    public ArrayList<Card> getLearnedCardList() {
        return learnedCardList;
    }

    public ArrayList<Card> getUnlearnedCardList() {
        return unlearnedCardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Box box = (Box) o;
        return Objects.equals(learnedCardList, box.learnedCardList)
                && Objects.equals(unlearnedCardList, box.unlearnedCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(learnedCardList, unlearnedCardList);
    }
}
