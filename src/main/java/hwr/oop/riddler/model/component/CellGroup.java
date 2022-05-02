package hwr.oop.riddler.model.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CellGroup {

    private final List<Cell> cells;

    public CellGroup(List<Cell> contents) {
        this.cells = new ArrayList<>(contents);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Set<Integer> getAllValues() {
        var values = new HashSet<Integer>();
        for (Cell cell : cells) {
            if (cell.isFilled()) {
                values.add(cell.getValue());
            }
        }
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellGroup cellGroup = (CellGroup) o;
        return cells.equals(cellGroup.cells);
    }

    @Override
    public int hashCode() {
        return cells.hashCode();
    }
}
