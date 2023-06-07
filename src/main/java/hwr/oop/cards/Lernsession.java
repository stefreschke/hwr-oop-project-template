package hwr.oop.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lernsession {
    private final List<NewBox> boxList;
    private final int NUMBER_OF_BOXES;
    private int currentBoxIndex;
    private Card currentCard;

    // saving Boxes reference or reference to the box
    public static Lernsession createLernsessionWith3Boxes(){
        Boxes boxes = Boxes.createBoxes(3);
        return new Lernsession(List.of(boxes.retrieve(0).get(),boxes.retrieve(1).get(),boxes.retrieve(2).get()));
    }
    public static Lernsession createLernsessionWith5Boxes(){
        Boxes boxes = Boxes.createBoxes(5);
        return new Lernsession(List.of(boxes.retrieve(0).get(), boxes.retrieve(1).get(),boxes.retrieve(2).get(),boxes.retrieve(3).get(), boxes.retrieve(4).get()));
    }public static Lernsession createLernsessionWith7Boxes(){
        Boxes boxes = Boxes.createBoxes(7);
        return new Lernsession(List.of(boxes.retrieve(0).get(), boxes.retrieve(1).get(),boxes.retrieve(2).get(),boxes.retrieve(3).get(), boxes.retrieve(4).get(),boxes.retrieve(5).get(), boxes.retrieve(6).get()));
    }
    public static Lernsession createLernsessionFromBoxList(List<NewBox> boxList){
        return new Lernsession(boxList);
    }

    private Lernsession(List<NewBox> boxList) {
        this.boxList = boxList;
        this.NUMBER_OF_BOXES = boxList.size();
    }

    public List<NewBox> getBoxList() {
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
        NewBox box;
        while (true) { //was soll passieren, wenn jede Box leer ist
            box = this.boxList.get(getRandomBoxIndex());
            if (!box.isEmpty_learned()) { //eigentlich unlearned,aber für Test so
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
    /*
    void moveCardUp(Card card) {
        getBoxList().get(currentBoxIndex).moveCardUp(card);
    }
    void moveCardDown(Card card) {
        getBoxList().get(currentBoxIndex).moveCardUp(card);
    }*/

    public Card getCurrentCard(){
        return this.currentCard;
    }
}
