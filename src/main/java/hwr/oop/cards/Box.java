package hwr.oop.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Box {

    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> notLearnedCardList;

    public Box(){

        learnedCardList = new ArrayList<Card>();
        notLearnedCardList = new ArrayList<Card>();
    }

    public Box(ArrayList<Card> learnedCardList, ArrayList<Card> notLearnedCardList) {

        this.learnedCardList = learnedCardList;
        this.notLearnedCardList = notLearnedCardList;
    }

    public void addCard(Card card) {
        learnedCardList.add(card);
    }

    public ArrayList<Card> getCardList() {

        ArrayList<Card> returnList = new ArrayList<Card>();

        returnList.addAll(learnedCardList);
        returnList.addAll(notLearnedCardList);

        return returnList;
    }

    public Card getRandomCard() {
        Random random = new Random();
        int index = random.nextInt(learnedCardList.size());
        Card returnCard = learnedCardList.get(index);
        learnedCardList.remove(index);
        return returnCard;
    }

    public ArrayList<Card> getLearnedCardList() {
        return learnedCardList;
    }

    public ArrayList<Card> getNotLearnedCardList() {
        return notLearnedCardList;
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
                && Objects.equals(notLearnedCardList, box.notLearnedCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(learnedCardList, notLearnedCardList);
    }
}
