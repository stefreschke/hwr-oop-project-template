package hwr.oop.Cards;

import java.util.ArrayList;

public class Topic {

    private ArrayList<Box> boxList;
    private String name;

    public Topic(String name){
        this.name = name;

        boxList = new ArrayList<Box>();
    }
    public String getName() {
        return name;
    }

    public void addBox(Box box) {
        boxList.add(box);
    }

    public Box getBoxOfIndex(int i) {
        return boxList.get(i);
    }
}
