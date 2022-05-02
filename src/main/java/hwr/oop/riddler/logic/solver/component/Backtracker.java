package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.logic.SudokuValidator;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.ArrayDeque;
import java.util.Deque;

public class Backtracker implements IterativeSudokuSolver {

    private final Deque<Cell> backtrackingStack = new ArrayDeque<>();
    private final SudokuValidator validator = new SudokuValidator();

    @Override
    public boolean doSolvingStep(Sudoku sudoku) {
        Cell cell;
        while ((cell = getNextUnsolvedCell(sudoku)) != null) {
            cell.setAssumedValue(cell.getSingleAssumedPossible());
            backtrackingStack.push(cell);

            if (validator.isValid(sudoku) && backtrack()) {
                return true;
            }
        }
        return false;
    }

    private boolean backtrack() {
        Cell cell = backtrackingStack.pop();
        cell.removeAssumedPossible(cell.getAssumedValue());

        while (cell.getAssumedPossibles().isEmpty() && !backtrackingStack.isEmpty()) {
            cell.resetAssumptions();
            cell = backtrackingStack.pop();
            cell.removeAssumedPossible(cell.getAssumedValue());
        }

        if (backtrackingStack.isEmpty()) {
            cell.removePossible(cell.getAssumedValue());
            cell.resetAssumptions();
            return true;
        }
        cell.resetAssumedValue();
        return false;
    }

    private Cell getNextUnsolvedCell(Sudoku sudoku) {
        var cells = sudoku.getAllCells();
        for (int i = backtrackingStack.size(); i < cells.size(); i++) {
            var cell = cells.get(i);
            if (!cell.isFilled()) return cell;
        }
        return null;
    }
}
