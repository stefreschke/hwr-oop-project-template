package hwr.oop.riddler.model.component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
public class Cell {
    private int value;
    @Getter
    private Set<Integer> possibles;

    public Cell(int value) {
        //TODO value between 1 and 9
        this.value = value;
    }

    public Cell() {
        this.possibles = new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        resetAssumptions();
    }


    @Getter
    @Setter
    private int assumedValue;
    @Getter
    @Setter
    private Set<Integer> assumedPossibles;

    public void resetAssumptions() {
        this.assumedPossibles = new HashSet<>(possibles);
        this.assumedValue = 0;
    }

    public void resetAssumedValue() {
        this.assumedValue = 0;
    }


    public boolean isFilled() {
        return value != 0;
    }

    public void setValue(int value) {
        if (this.value != 0) throw new IllegalArgumentException("Value was already set");
        this.value = value;
        this.assumedValue = 0;
    }

    public boolean removePossible(int possible) {
        removeAssumedPossible(possible);
        possibles.remove(possible);
        return false;
    }

    public boolean removeAssumedPossible(int possible) {
        if (assumedPossibles == null) return false;
        return assumedPossibles.remove(possible);
    }

    public boolean removeAllPossibles(Set<Integer> allValues) {
        assumedPossibles.removeAll(allValues);
        return possibles.removeAll(allValues);
    }

    public int getValue() {
        if (value != 0) return value;
        if (assumedValue != 0) return assumedValue;
        return 0;
    }

    public boolean hasAssumedPossibles() {
        return !assumedPossibles.isEmpty();
    }

    public int getSingleAssumedPossible() {
        return assumedPossibles.iterator().next();
    }
}
