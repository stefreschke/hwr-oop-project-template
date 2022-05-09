package hwr.oop.riddler.model.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CellGroupTest {
    CellGroup cellGroup;
    Cell[] cells;

    @BeforeEach
    private void setup() {
        cells = new Cell[]{
                new Cell(),
                new Cell(),
                new Cell(3),
                new Cell(4),
        };

        cellGroup = new CellGroup(Set.of(cells));
    }

    @Test
    void cellGroup_getCells() {
        assertEquals(Set.of(cells), cellGroup.getCells());
    }

    @Test
    void cellGroup_getUnsolvedCells() {
        assertEquals(Set.of(cells[0], cells[1]), cellGroup.getUnsolvedCells());
    }

    @Test
    void cellGroup_getAllValues() {
        assertEquals(Set.of(3, 4), cellGroup.getAllValues());
    }

    @Test
    void cellGroup_equalCellGroupsAreEqual() {
        var cellGroup2 = new CellGroup(Set.of(cells));
        assertEquals(cellGroup, cellGroup2);
    }

    @Test
    void cellGroup_differentCellGroupsAreNotEqual() {
        Cell[] otherCells = {
                new Cell(1),
                new Cell(2),
                new Cell(3),
                new Cell(5),
        };
        var otherCellGroup = new CellGroup(Set.of(otherCells));
        assertNotEquals(cellGroup, otherCellGroup);
    }

    @Test
    void cellGroup_hashCode() {
        int hashcode = cellGroup.hashCode();
        assertEquals(hashcode, cellGroup.hashCode());
    }
}
