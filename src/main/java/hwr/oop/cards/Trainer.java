package hwr.oop.cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trainer {
    private final List<Box> boxList;
    private final int NUMBER_OF_BOXES;
    private int currentBoxIndex;
    private Card currentCard;

    public static Trainer createTrainerWith3Boxes(){
        return new Trainer(List.of(new Box(), new Box(), new Box()));
    }public static Trainer createTrainerWith5Boxes(){
        return new Trainer(List.of(new Box(), new Box(), new Box(), new Box(), new Box()));
    }public static Trainer createTrainerWith7Boxes(){
        return new Trainer(List.of(new Box(), new Box(), new Box(), new Box(), new Box(), new Box(), new Box()));
    }
    public static Trainer createTrainerFromBoxList(List<Box> boxList){
        return new Trainer(boxList);
    }

    private Trainer(List<Box> boxList) {
        this.boxList = boxList;
        this.NUMBER_OF_BOXES = boxList.size();
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public int getRandomBoxIndex(){
        Random random = new Random();
        int randomInt = random.nextInt(NUMBER_OF_BOXES - 1);
        this.currentBoxIndex = randomInt;
        return randomInt;
    }

    public int getCurrentBoxIndex(){
        return this.currentBoxIndex;
    }

    public Card getRandomCard(){
        return (this.boxList.get(getRandomBoxIndex()).getRandomCard());
    }


    // loading new Topic
    void loadTopic(Topic topic) {
        ArrayList<Card> cardList = topic.getCardList();
        for (Card card : cardList) {
            boxList.get(0).addCard(card);
        }
    }
    void moveCardUp(Card card) {
        //Bei Tests bleibt currentBoxIndex bei 0!!!
        if (currentBoxIndex != NUMBER_OF_BOXES - 1){
            getBoxList().get(currentBoxIndex+1).addCard(card);
            this.currentBoxIndex++; //für Testzwecke
        }
        else{
            getBoxList().get(currentBoxIndex).addCard(card);
        }
    }
    void moveCardDown(Card card) {
        if (currentBoxIndex != 0){
            getBoxList().get(currentBoxIndex-1).addCard(card);
            this.currentBoxIndex--; //für Testzwecke
        }
        else{
            getBoxList().get(currentBoxIndex).addCard(card);
        }
    }


    /*
    boolean checkAnswer(String answer){
        if answer == card.getAnswer(){
            moveCardUp();
        }
    }
    */
}
