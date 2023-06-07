package hwr.oop.cards;

import java.util.*;

public class Boxes {
    private final Map<Integer, NewBox> boxMap;

    public static Boxes createBoxes(int numberOfBoxes){
        if (numberOfBoxes==3 | numberOfBoxes==5 | numberOfBoxes==7){
            return new Boxes(numberOfBoxes);
        }else{
            throw new InvalidBoxNumberException();
        }
    }
    private Boxes(int numberOfBoxes) {
        this.boxMap = createMap(numberOfBoxes);
    }

    public Boxes(List<NewBox> boxen){
        this.boxMap = createMapFromBoxList(boxen);
    }

    private Map<Integer, NewBox> createMapFromBoxList(List<NewBox> boxen) {
        final Map<Integer, NewBox> map = new HashMap<>();
        for (int current = 0; current < boxen.size(); current++){
            final int next = current + 1;
            final int previous = current - 1;
            final NewBox loadedBox = boxen.get(current);
            final ArrayList<Card> learnedCardList = loadedBox.getLearnedCardList();
            final ArrayList<Card> unlearnedCardList = loadedBox.getUnlearnedCardList();
            final int learnInterval = loadedBox.getLearnInterval();
            final NewBox box = new NewBox(learnedCardList, unlearnedCardList, learnInterval, this, next, previous );
            map.put(current, box);
        }
        return map;
    }

    private Map<Integer, NewBox> createMap(int numberOfBoxes) {
        final Map<Integer, NewBox> map = new HashMap<>();
        final Map<Integer, ArrayList<Integer>> learnIntervalMap = createLearnIntervalMap();
        for (int current = 0; current < numberOfBoxes; current++) {
            int learnInterval = learnIntervalMap.get(numberOfBoxes).get(current);
            final int next = current + 1;
            final int previous = current - 1;
            final NewBox box = new NewBox(learnInterval,this, next, previous);
            map.put(current, box);
        }
        return map;
    }

    private Map<Integer, ArrayList<Integer>> createLearnIntervalMap() {
        final Map<Integer, ArrayList<Integer>> learnIntervalMap = new HashMap<>();
        learnIntervalMap.put(3,new ArrayList<Integer>(List.of(1, 3, 7)));
        learnIntervalMap.put(5,new ArrayList<Integer>(List.of(1, 3, 7, 14, 30)));
        learnIntervalMap.put(7,new ArrayList<Integer>(List.of(1, 3, 7, 14, 30, 45, 60)));
        return learnIntervalMap;
    }

    public Optional<NewBox> retrieve(int key) {
        if (boxMap.containsKey(key)) {
            final NewBox value = boxMap.get(key);
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }
    public int getBoxAmount(){
        return this.boxMap.size();
    }

    public List<NewBox> createBoxList(){
        List<NewBox> boxList = new ArrayList<>();
        for (int current = 0; current < boxMap.size(); current++){
            boxList.add(boxMap.get(current));
        }
        return boxList;
    }
    public
    static class InvalidBoxNumberException extends RuntimeException{
        private InvalidBoxNumberException() {
            super("BoxNumber should be either 3, 5 or 7");
        }
    }
}
