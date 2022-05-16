package hwr.oop.riddler.model.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CellGroupTest {
    CellGroup cellGroup;
    List<Cell> cells;

    @BeforeEach
    private void setup() {
        cells = List.of(
                new Cell(new CellPosition(0, 0)),
                new Cell(new CellPosition(0, 1)),
                new Cell(3, new CellPosition(1, 0)),
                new Cell(4, new CellPosition(1, 1))
        );
        cellGroup = new CellGroup(new HashSet<>(cells), CellGroupType.BOX);
    }

    @Test
    void cellGroup_getCells() {
        assertEquals(new HashSet<>(cells), cellGroup.getCells());
    }

    @Test
    void cellGroup_getUnsolvedCells() {
        assertEquals(Set.of(cells.get(0), cells.get(1)), cellGroup.getUnsolvedCells());
    }

    @Test
    void cellGroup_getAllValues() {
        assertEquals(Set.of(3, 4), cellGroup.getAllValues());
    }

    @Test
    void cellGroup_equalCellGroupsAreEqual() {
        var cellGroup2 = new CellGroup(new HashSet<>(cells), CellGroupType.BOX);
        assertEquals(cellGroup, cellGroup2);
    }

    @Test
    void cellGroup_differentCellGroupsAreNotEqual() {
        Set<Cell> otherCells = Set.of(
                new Cell(1, new CellPosition(0, 0)),
                new Cell(2, new CellPosition(0, 1)),
                new Cell(3, new CellPosition(1, 0)),
                new Cell(5, new CellPosition(1, 1))
                );
        var otherCellGroup = new CellGroup(otherCells, CellGroupType.ROW);
        assertNotEquals(cellGroup, otherCellGroup);
    }

    @Test
    void cellGroup_hashCode() {
        int hashcode = cellGroup.hashCode();
        assertEquals(hashcode, cellGroup.hashCode());
    }
}
