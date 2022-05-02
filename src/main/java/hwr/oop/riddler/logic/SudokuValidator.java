package hwr.oop.riddler.logic;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {
    private Sudoku sudoku;

    public boolean isValid(Sudoku sudoku) {
        this.sudoku = sudoku;
        return rowsAreValid() && columnsAreValid() && boxesAreValid();
    }

    private boolean rowsAreValid(){
        for (CellGroup row : sudoku.getRows()) {
            if (hasDuplicate(row)) {
                return false;
            }
        }
        return true;
    }

    private boolean columnsAreValid() {
        for (CellGroup column : sudoku.getColumns()) {
            if (hasDuplicate(column)) {
                return false;
            }
        }
        return true;
    }

    private boolean boxesAreValid() {
        for (CellGroup box : sudoku.getBoxes()) {
            if (hasDuplicate(box)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDuplicate(CellGroup testSubject) {
        Set<Integer> encounteredValues = new HashSet<>();
        for (Cell cell : testSubject.getCells()) {
            if (cell.isFilled()) {
                if (encounteredValues.contains(cell.getValue())) {
                    return true;
                }
                encounteredValues.add(cell.getValue());
            }
        }
        return false;
    }
}
