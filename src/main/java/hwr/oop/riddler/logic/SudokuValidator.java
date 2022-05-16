package hwr.oop.riddler.logic;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class SudokuValidator {
    private final Sudoku sudoku;

    public SudokuValidator(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean isValid() {
        return cellGroupsAreValid() && unsolvedCellsHavePossibles();
    }

    public boolean isInvalid() {
        return !isValid();
    }

    private boolean unsolvedCellsHavePossibles() {
        return sudoku
                .getUnsolvedCells()
                .stream()
                .allMatch(this::hasPossibles);

    }

    private boolean cellGroupsAreValid() {
        return sudoku
                .getCellGroups()
                .stream()
                .allMatch(Predicate.not(this::hasDuplicate));
    }

    private boolean hasPossibles(Cell cell) {
        return cell.getImpossibles().size() < sudoku.getSize();
    }

    private boolean hasDuplicate(CellGroup testSubject) {
        Set<Integer> encounteredValues = new HashSet<>();
        for (Cell cell : testSubject.getCells()) {
            if (cell.isFilled()) {
                if (encounteredValues.contains(cell.getValue()))
                    return true;
                encounteredValues.add(cell.getValue());
            }
        }
        return false;
    }
}
