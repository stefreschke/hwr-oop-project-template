package hwr.oop.cards;

import java.util.*;

public class Boxes7 {
    private final Map<Integer, NewBox> boxMap;

    public Boxes7(int numberOfBoxes) {
        this.boxMap = createMap(numberOfBoxes);
    }

    private Map<Integer, NewBox> createMap(int numberOfBoxes) {
        final Map<Integer,NewBox> map = new HashMap<>();
        ArrayList<Integer> boxes = new ArrayList<>(List.of(1, 3, 7, 14, 30, 45, 60));
        for (int current = 0; current < numberOfBoxes; current++) {
            final int next = current + 1;
            final int previous = current - 1;
            final NewBox box = new NewBox(boxes.get(current),(Boxes)this, next, previous);
            map.put(current, box);
        }
        return map;
    }

    public Optional<NewBox> retrieve(int key) {
        if (boxMap.containsKey(key)) {
            final NewBox value = boxMap.get(key);
            return Optional.of(value);
        } else {
            return Optional.empty();
        }
    }
}

