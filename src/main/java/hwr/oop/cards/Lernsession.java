package hwr.oop.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lernsession {
    private final Boxes mediator;
    private final int NUMBER_OF_BOXES;
    private int currentBoxIndex;
    private Card currentCard;

    // saving Boxes reference or reference to the box
    public static Lernsession createLernsessionWith3Boxes(){
        Boxes boxes = Boxes.createBoxes(3);
        return new Lernsession(boxes);
    }
    public static Lernsession createLernsessionWith5Boxes(){
        Boxes boxes = Boxes.createBoxes(5);
        return new Lernsession(boxes);
    }public static Lernsession createLernsessionWith7Boxes(){
        Boxes boxes = Boxes.createBoxes(7);
        return new Lernsession(boxes);
    }
    public static Lernsession createLernsessionFromBoxes(Boxes boxes){
        return new Lernsession(boxes);
    }

    private Lernsession(Boxes mediator) {
        this.mediator = mediator;
        this.NUMBER_OF_BOXES = mediator.getBoxAmount();
        currentBoxIndex = 0;
    }

    public Boxes getBoxes() {
        return mediator;
    }

    public int getRandomBoxIndex(){
        Random random = new Random();
        int randomInt = random.nextInt(NUMBER_OF_BOXES - 1);
        this.currentBoxIndex = randomInt;
        return randomInt;
    }

    public Card getRandomCard(){
        NewBox box;
        while (true) { //was soll passieren, wenn jede Box leer ist
            box = mediator.retrieve(getRandomBoxIndex()).get();
            if (!box.isEmpty_learned()) { //eigentlich unlearned,aber für Test so
                break;
            }
        }
        return box.getRandomCard();
    }

    // loading new Topic
    void loadTopic(Topic topic) {
        ArrayList<Card> cardList = topic.getCardList();
        NewBox box = mediator.retrieve(0).get();
        for (Card card : cardList) {
            box.addCard(card);
        }
    }
}
