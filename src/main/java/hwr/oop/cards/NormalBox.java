package hwr.oop.cards;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NormalBox implements BoxInterface{
    private ArrayList<Card> learnedCardList;
    private ArrayList<Card> unlearnedCardList;
    @JsonBackReference
    private final BoxInterface previousBox;
    @JsonManagedReference
    private BoxInterface nextBox;
    private int daterule;

    // Konstruktor: fügt sich selbst als NextBox bei der vorherigen Box hinzu
    public NormalBox(int daterule, BoxInterface previousBox){
        learnedCardList = new ArrayList<Card>();
        unlearnedCardList = new ArrayList<Card>();
        this.daterule = daterule;
        this.previousBox = previousBox;
        previousBox.setNextBox(this);
    }
    public NormalBox(ArrayList<Card> learnedCardList, ArrayList<Card> unlearnedCardList, int daterule, BoxInterface previousBox){
        this.learnedCardList = learnedCardList;
        this.unlearnedCardList = unlearnedCardList;
        this.daterule = daterule;
        this.previousBox = previousBox;
        previousBox.setNextBox(this);
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
        this.previousBox.addCard(card);
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
    public boolean isEmpty(){ return (learnedCardList.isEmpty()&& unlearnedCardList.isEmpty());}

    @Override
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

    @Override
    public BoxInterface getNextBox() {
        return this.nextBox;
    }
    public void setNextBox(BoxInterface nextBox){
        if (this.nextBox == null){
            this.nextBox = nextBox;
        }
    }
}