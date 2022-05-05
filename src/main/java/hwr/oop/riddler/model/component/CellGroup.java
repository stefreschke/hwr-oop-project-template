package hwr.oop.riddler.model.component;

import java.util.HashSet;
import java.util.Set;

// A CellGroup is either a Row, Column, or a Box. These are treated Identically. A polymorphic implementation would
// have lead to duplicate code.
public class CellGroup {
    private final Set<Cell> cells;

    public CellGroup(Set<Cell> contents) {
        this.cells = new HashSet<>(contents);
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public Set<Cell> getUnsolvedCells() {
        var unsolvedCells = new HashSet<Cell>();
        for (Cell cell : cells) {
            if (cell.isEmpty())
                unsolvedCells.add(cell);
        }
        return unsolvedCells;
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
