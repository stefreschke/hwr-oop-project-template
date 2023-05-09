package hwr.oop.cards;

import java.util.ArrayList;
import java.util.Random;

public class Box {

    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> notLearnedCardList;

    public Box(){
        learnedCardList = new ArrayList<Card>();
        notLearnedCardList = new ArrayList<Card>();
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
}
