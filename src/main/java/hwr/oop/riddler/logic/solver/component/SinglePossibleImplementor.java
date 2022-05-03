package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;

public class SinglePossibleImplementor extends SolvingComponent {
    public SinglePossibleImplementor(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    public boolean execute() {
        boolean changesMade = false;
        for (Cell cell : sudoku.getAllCells()) {
            changesMade = fillCellWithOnlyPossibleValue(cell);
        }
        return changesMade;
    }

    private boolean fillCellWithOnlyPossibleValue(Cell cell) {
        if (cell.isFilled() || !cellHasOnePossibleValue(cell))
            return false;

        cell.setValue(onlyPossibleValue(cell));
        return true;
    }

    private boolean cellHasOnePossibleValue(Cell cell) {
        return sudoku.getSize() - cell.getImpossibles().size() == 1;
    }

    private int onlyPossibleValue(Cell cell) {
        for (int value = 1; value <= sudoku.getSize(); value++) {
            if (!cell.getImpossibles().contains(value))
                return value;
        }
        throw new IllegalArgumentException("cell has no possible value");
    }
}
