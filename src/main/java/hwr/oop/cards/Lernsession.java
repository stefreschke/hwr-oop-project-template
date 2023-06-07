package hwr.oop.cards;

import java.util.*;

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

    public int getRandomBoxIndex() {
        Random random = new Random();
        int randomInt = random.nextInt(NUMBER_OF_BOXES - 1);
        this.currentBoxIndex = randomInt;
        return randomInt;
    }
    public int getRandomBoxIndexFromList(List<Integer> indexList){
        Random random = new Random();
        int randomInt = indexList.get(random.nextInt(indexList.size()));
        this.currentBoxIndex = randomInt;
        return randomInt;
    }

    public Card getRandomCard(){
        NewBox box;
        List<Integer> indexList = new ArrayList<>();
        for(int current = 0; current < NUMBER_OF_BOXES; current++){
            indexList.add(current);
        }
        while (!indexList.isEmpty()) {
            Integer index = getRandomBoxIndexFromList(indexList); //keine int, da bei remove sonst index statt Object
            indexList.remove(index);
            box = mediator.retrieve(index).get();
            if (!box.isEmpty_learned()) { //eigentlich unlearned,aber für Test so
                return box.getRandomCard();
            }
        }
        throw new EmptyBoxesException();
    }

    // loading new Topic
    void loadTopic(Topic topic) {
        ArrayList<Card> cardList = topic.getCardList();
        NewBox box = mediator.retrieve(0).get();
        for (Card card : cardList) {
            box.addCard(card);
        }
    }
    public static class EmptyBoxesException extends RuntimeException{
        public EmptyBoxesException(){
            super ("All Boxes are empty!");
        }
    }
}
