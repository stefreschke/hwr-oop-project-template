package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.ArrayDeque;
import java.util.Deque;

public class Backtracker extends SolvingComponent {

    private final Deque<Sudoku> sudokuBackups = new ArrayDeque<>();

    public Backtracker(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    public boolean execute() {
        if (sudoku.isFilled())
            return false;

        Cell cell = getNextUnsolvedCell();
        if (cellHasAPossibleValue(cell)) {
            int assumedValue = getAPossibleValue(cell);
            cell.addImpossible(assumedValue);
            sudokuBackups.push(new Sudoku(sudoku));
            cell.setValue(assumedValue);
        } else {
            backtrack();
        }

        return true;
    }

    private void backtrack() {
        sudoku = sudokuBackups.pop();
    }

    private Cell getNextUnsolvedCell() {
        var unsolvedCells = sudoku.getAllUnsolvedCells();
        return unsolvedCells.get(0);
    }


    private int getAPossibleValue(Cell cell) {
        for (int value = 1; value <= sudoku.getSize(); value++) {
            if (!cell.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalStateException("empty cell has no possible values");
    }

    private boolean cellHasAPossibleValue(Cell cell) {
        return sudoku.getSize() - cell.getImpossibles().size() > 1;
    }
}
