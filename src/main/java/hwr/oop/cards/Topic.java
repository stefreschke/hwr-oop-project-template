package hwr.oop.cards;

import java.util.ArrayList;

public class Topic {

    private ArrayList<Box> boxList;
    private String name;
    private int activeBoxIndex;

    public Topic(String name, int amount){

        if (amount != 3 && amount != 5 && amount != 7) amount = 3;

        this.name = name;

        boxList = new ArrayList<Box>();

        for (int i = 0; i < amount; i++){

            boxList.add(new Box());
        }

        activeBoxIndex = 0;
    }
    public String getName() {
        return name;
    }

    public int boxCount() {
        return boxList.size();
    }

    public void addCard(Card card) {
        boxList.get(0).addCard(card);
    }

    public Box getActiveBox() {
        return boxList.get(activeBoxIndex);

    }
}
