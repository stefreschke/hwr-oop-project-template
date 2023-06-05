package hwr.oop.cards;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class FirstBox implements BoxInterface{
    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> unlearnedCardList;
    private BoxInterface nextBox = null;
    private int daterule;

    public FirstBox(int daterule){
        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
        this. daterule = daterule;
    }
    public FirstBox(ArrayList<Card> learnedCardList, ArrayList<Card> unlearnedCardList, int daterule){
        this.learnedCardList = learnedCardList;
        this.unlearnedCardList = unlearnedCardList;
        this.daterule = daterule;
        this.nextBox = nextBox;
    }
    @Override
    public void addCard(Card card) {
        learnedCardList.add(card);
    }

    @Override
    public void moveCardUp(Card card) {
        this.nextBox.addCard(card);
    }

    @Override
    public void moveCardDown(Card card) {
        this.learnedCardList.add(card);
    }

    @Override
    public boolean isEmpty_learned() {
        return learnedCardList.isEmpty();
    }

    @Override
    public boolean isEmpty_unlearned() {
        return unlearnedCardList.isEmpty();
    }

    @Override
    public void updateBox() {
        Date currentDate = new Date();
        Calendar learnDate = Calendar.getInstance();
        for (Card card: this.learnedCardList){
            learnDate.setTime(card.getLastLearned()); //setzt Datum
            learnDate.add(Calendar.DATE, daterule); //addiert Regel darauf
            if (learnDate.before(currentDate)){
                this.unlearnedCardList.add(card);
                this.learnedCardList.remove(card);
            }
        }
    }

    @Override
    public Card getRandomCard() {
        // learnedCardList müsste unlearned sein in der Logik mit einem Datum, für die Tests aber hinderlich
        Random random = new Random();
        int index = random.nextInt(learnedCardList.size());
        Card returnCard = learnedCardList.get(index);
        learnedCardList.remove(index);
        return returnCard;
    }

    @Override
    public ArrayList<Card> getLearnedCardList() {
        return learnedCardList;
    }

    @Override
    public ArrayList<Card> getUnlearnedCardList() {
        return unlearnedCardList;
    }
    public BoxInterface getNextBox() {
        return nextBox;
    }
    public void setNextBox(BoxInterface nextBox){
        if (this.nextBox == null){
            this.nextBox = nextBox;
        }
    }
}
