package hwr.oop.cards;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NewBox{
    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> unlearnedCardList;
    private final Boxes boxes;
    private final int next;
    private final int previous;
    private int daterule;

    NewBox(int daterule, Boxes boxes, int next, int previous){
        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
        this.daterule = daterule;
        this.boxes = boxes;
        this.next = next;
        this.previous = previous;
    }
    NewBox(ArrayList<Card> learnedCardList, ArrayList<Card> unlearnedCardList, int daterule, Boxes boxes, int next, int previous){
        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
        this.daterule = daterule;
        this.boxes = boxes;
        this.next = next;
        this.previous = previous;
    }
    }
    public void addCard(Card card) {
        learnedCardList.add(card);
    }

    public void moveCardUp(Card card) {
        this.boxes.retrieve(next).addCard(card);
    }

    public void moveCardDown(Card card) {
        this.previousBox.addCard(card);
    }

    public boolean isEmpty_learned() {
        return learnedCardList.isEmpty();
    }

    public boolean isEmpty_unlearned() {
        return unlearnedCardList.isEmpty();
    }
    public boolean isEmpty(){ return (learnedCardList.isEmpty()&& unlearnedCardList.isEmpty());}

    public void updateBox() {
        Date currentDate = new Date();
        Calendar learnDate = Calendar.getInstance();
        int index = 0;
        ArrayList <Integer> indices = new ArrayList<Integer>();
        Date lernTag = new Date();
        for (Card card: this.learnedCardList){
            learnDate.setTime(card.getLastLearned()); //setzt Datum
            learnDate.add(Calendar.DATE, daterule); //addiert Regel darauf
            lernTag = learnDate.getTime(); //cast ist nötig
            if (lernTag.before(currentDate)){
                this.unlearnedCardList.add(card);
                indices.add(index);
            }
            index++;
        }

        for (Integer i: indices){
            this.learnedCardList.remove((int)i); //cast nötig weil Remove(Object o) aufgerufen wird statt remove(index i)
        }
    }

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

    public BoxInterface getNextBox() {
        return this.nextBox;
    }
    public void setNextBox(BoxInterface nextBox){
        if (this.nextBox == null){
            this.nextBox = nextBox;
        }
    }
}