package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

public class SinglePossibleImplementor implements IterativeSudokuSolver {
    @Override
    public boolean doSolvingStep(Sudoku sudoku) {
        for (Cell cell : sudoku.getAllCells()) {
            if (!cell.isFilled() && cell.getPossibles().size() == 1) {
                cell.setValue(cell.getPossibles().iterator().next());
                return true;
            }
        }
        return false;
    }
}
