package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.logic.SudokuValidator;
import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

import java.util.ArrayDeque;
import java.util.Deque;

public class Backtracker extends SolvingComponent {

    private final Deque<Sudoku> sudokuBackups = new ArrayDeque<>();
    private final SudokuValidator validator = new SudokuValidator();

    public Backtracker(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    public boolean execute() {
        if (sudoku.isFilled()) {
            //System.out.println("jup, this sudoku is totally done, man, i swear:");
            return false;
        }

        if (validator.isValid(sudoku))
            continueSolvingWithAssumedValue();
        else
            backtrack();

        return true;
    }

    private void continueSolvingWithAssumedValue() {
        Cell targetCell = getNextUnsolvedCell();
        int assumedValue = getAPossibleValue(targetCell);

        targetCell.addImpossible(assumedValue);
        sudokuBackups.push(new Sudoku(sudoku));

        targetCell.setValue(assumedValue);
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
}
