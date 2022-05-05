package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

public class PossiblesEliminator extends SolvingComponent {
    boolean changesWereMade;

    public PossiblesEliminator(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    public boolean execute() {
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
