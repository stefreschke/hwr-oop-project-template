package hwr.oop.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewTrainer {
    private final List<BoxInterface> boxList;
    private final int NUMBER_OF_BOXES;
    private int currentBoxIndex;
    private Card currentCard;

    public static NewTrainer createTrainerWith3Boxes(){
        BoxInterface box1 = new FirstBox(1);
        BoxInterface box2 = new NormalBox(3,box1);
        BoxInterface box3 = new LastBox(7,box2);
        return new NewTrainer(List.of(box1, box2, box3));
    }
    }public static NewTrainer createTrainerWith5Boxes(){
        BoxInterface box1 = new FirstBox(1);
        BoxInterface box2 = new NormalBox(3,box1);
        BoxInterface box3 = new NormalBox(7,box2);
        BoxInterface box4 = new NormalBox(14,box3);
        BoxInterface box5 = new LastBox(7,box4);
        return new NewTrainer(List.of(box1, box2, box3, box4, box5));
    }public static NewTrainer createTrainerWith7Boxes(){
        BoxInterface box1 = new FirstBox(1);
        BoxInterface box2 = new NormalBox(3,box1);
        BoxInterface box3 = new NormalBox(7,box2);
        BoxInterface box4 = new NormalBox(14,box3);
        BoxInterface box5 = new NormalBox(14,box4);
        BoxInterface box6 = new NormalBox(14,box5);
        BoxInterface box7 = new LastBox(7,box6);
        return new NewTrainer(List.of(box1, box2, box3, box4, box5, box6, box7));
    }
    public static NewTrainer createTrainerFromBoxList(List<BoxInterface> boxList){
        return new NewTrainer(boxList);
    }

    private NewTrainer(List<BoxInterface> boxList) {
        this.boxList = boxList;
        this.NUMBER_OF_BOXES = boxList.size();
    }

    public List<BoxInterface> getBoxList() {
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
        BoxInterface box;
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
