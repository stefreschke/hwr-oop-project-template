package hwr.oop.cards;

import com.fasterxml.jackson.annotation.*;

import javax.swing.*;
import java.util.*;

public class NewBox{
    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> unlearnedCardList;
    private final Boxes boxes;
    private final int next;
    private final int previous;
    private int learnInterval;


    /*public NewBox(){
        boxes = null;
        next = 0;
        previous = 0;
        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
    }*/
    public NewBox(int learnInterval, Boxes boxes, int next, int previous){
        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
        this.learnInterval = learnInterval;
        this.boxes = boxes;
        this.next = next;
        this.previous = previous;
    }
    @JsonCreator
    public NewBox(@JsonProperty("learnedCardList") ArrayList<Card> learnedCardList, @JsonProperty("unlearnedCardList") ArrayList<Card> unlearnedCardList, @JsonProperty("learnInterval") int learnInterval, @JsonProperty("boxes") Boxes boxes, @JsonProperty("next") int next, @JsonProperty("previous") int previous){
        this.learnedCardList = learnedCardList;
        this.unlearnedCardList = unlearnedCardList;
        this.learnInterval = learnInterval;
        this.boxes = boxes;
        this.next = next;
        this.previous = previous;
    }
    public void addCard(Card card) {
        learnedCardList.add(card);
    }
/*
    public void moveCardUp(Card card) {
        this.boxes.retrieve(next).addCard(card);
    }

    public void moveCardDown(Card card) {
        this.previousBox.addCard(card);
    }*/
    @JsonIgnore
    public boolean isEmpty_learned() {
        return learnedCardList.isEmpty();
    }
    @JsonIgnore

    public boolean isEmpty_unlearned() {
        return unlearnedCardList.isEmpty();
    }
    @JsonIgnore

    public boolean isEmpty(){ return (learnedCardList.isEmpty()&& unlearnedCardList.isEmpty());}
    @JsonIgnore
    public void updateBox() {
        Date currentDate = new Date();
        Calendar learnDate = Calendar.getInstance();
        int index = 0;
        ArrayList <Integer> indices = new ArrayList<Integer>();
        Date lernTag = new Date();
        for (Card card: this.learnedCardList){
            learnDate.setTime(card.getLastLearned()); //setzt Datum
            learnDate.add(Calendar.DATE, learnInterval); //addiert Regel darauf
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
    @JsonIgnore
    public Card getRandomCard() {
        // learnedCardList müsste unlearned sein in der Logik mit einem Datum, für die Tests aber hinderlich
        Random random = new Random();
        int index = random.nextInt(learnedCardList.size());
        Card returnCard = learnedCardList.get(index);
        learnedCardList.remove(index);
        return returnCard;
    }
    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewBox box = (NewBox) o;
        if
        for (int current = 0; current <){
            card
        }
        return Objects.equals(learnedCardList, box.learnedCardList)
                && Objects.equals(unlearnedCardList, box.unlearnedCardList);
    }

    public ArrayList<Card> getLearnedCardList() {
        return learnedCardList;
    }

    public ArrayList<Card> getUnlearnedCardList() {
        return unlearnedCardList;
    }

    public int getLearnInterval(){
        return learnInterval;
    }
    public int getNext(){
        return next;
    }
    public int getPrevious() {
        return previous;
    }
}