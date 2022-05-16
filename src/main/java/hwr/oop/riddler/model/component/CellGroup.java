package hwr.oop.riddler.model.component;

import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A CellGroup is either a Row, Column, or a Box. These are treated Identically. A polymorphic implementation would
 * have lead to duplicate code.
 */
public class CellGroup {
    @Getter
    private final Set<Cell> cells;
    @Getter
    private final CellGroupType type;

    public CellGroup(Set<Cell> contents, CellGroupType type) {
        this.cells = new HashSet<>(contents);
        this.type = type;
    }

    public Set<Cell> getUnsolvedCells() {
        return cells
                .stream()
                .filter(Predicate.not(Cell::isFilled))
                .collect(Collectors.toSet());
    }

    public Set<Integer> getAllValues() {
        return cells
                .stream()
                .filter(Cell::isFilled)
                .map(Cell::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellGroup group = (CellGroup) o;
        return Objects.equals(cells, group.cells) && type == group.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells, type);
    }
}
