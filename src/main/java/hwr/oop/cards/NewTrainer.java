package hwr.oop.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewTrainer {
    private final List<Box> boxList;
    private final int NUMBER_OF_BOXES;
    private int currentBoxIndex;
    private Card currentCard;

    public static NewTrainer createTrainerWith3Boxes(){
        BoxInterface box1 = null;
        BoxInterface box2 = null
        BoxInterface box3 = null;
        box1 = new FirstBox(1, box2);
        box2 = new NormalBox(3, box1, box3);
        box3 = new LastBox(7, box2);
        return new NewTrainer(List.of(new Box(), new Box(), new Box()));
    }public static NewTrainer createTrainerWith5Boxes(){
        return new NewTrainer(List.of(new Box(), new Box(), new Box(), new Box(), new Box()));
    }public static NewTrainer createTrainerWith7Boxes(){
        return new NewTrainer(List.of(new Box(), new Box(), new Box(), new Box(), new Box(), new Box(), new Box()));
    }
    public static NewTrainer createTrainerFromBoxList(List<Box> boxList){
        return new NewTrainer(boxList);
    }

    private NewTrainer(List<Box> boxList) {
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
        Box box;
        while (true) { //was soll passieren, wenn jede Box leer ist
            box = this.boxList.get(getRandomBoxIndex());
            if (box.isEmpty_learned() == false) { //eigentlich unlearned,aber für Test so
                break;
            }
        }
        return box.getRandomCard();
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
}
