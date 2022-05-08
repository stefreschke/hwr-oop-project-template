package hwr.oop.riddler.logic.backtrackingsolver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

public class PossiblesEliminator implements SolvingComponent {
    boolean changesWereMade;

    @Override
    public boolean execute(Sudoku sudoku) {
        changesWereMade = false;

        for (CellGroup cellGroup : sudoku.getConcatenatedCellGroups()) {
            removePossibleCellValues(cellGroup);
        }

        return changesWereMade;
    }

    private void removePossibleCellValues(CellGroup cellGroup) {
        for (Cell cell : cellGroup.getUnsolvedCells()) {
            boolean addedImpossibles = cell.addImpossibles(cellGroup.getAllValues());
            if (addedImpossibles)
                changesWereMade = true;
        }
    }
}
