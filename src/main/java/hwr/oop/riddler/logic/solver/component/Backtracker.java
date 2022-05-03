package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.logic.SudokuValidator;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.ArrayDeque;
import java.util.Deque;

public class Backtracker extends SolvingComponent {

    //private final Deque<Sudoku> backtrackingStack = new ArrayDeque<>();
    private final SudokuValidator validator = new SudokuValidator();
    private Sudoku workingCopySudoku;

    public Backtracker(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    public boolean execute() {
        boolean changesMade = false;
        workingCopySudoku = new Sudoku(sudoku);

        for (Cell cell : workingCopySudoku.getAllUnsolvedCells()) {
            if (cellHasAPossibleValue(cell)) {
                cell.setValue(getAPossibleValue(cell));
                changesMade = true;
            }
            else
                backtrack();
        }

        return changesMade;
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

    private Cell getNextUnsolvedCell() {
        var cells = workingCopySudoku.getAllCells();
        for (int i = currentCellIndex; i < cells.size(); i++) {
            var cell = cells.get(i);
            if (!cell.isFilled())
                return cell;
        }
        return null;
    }


    private int getAPossibleValue(Cell cell) {
        for (int value = 1; value <= sudoku.getSize(); value++) {
            if(!cell.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalStateException("empty cell has no possible values");
    }

    private boolean cellHasAPossibleValue(Cell cell) {
        return sudoku.getSize() - cell.getImpossibles().size() > 1;
    }
}
