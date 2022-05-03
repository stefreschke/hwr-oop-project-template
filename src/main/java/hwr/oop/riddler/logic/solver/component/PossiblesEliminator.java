package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

public class PossiblesEliminator extends SolvingComponent {

    public PossiblesEliminator(Sudoku sudoku) {
        super(sudoku);
    }

    @Override
    public boolean execute() {
        boolean changesWereMade = false;

        for (CellGroup cellGroup : sudoku.getAllCellGroups()) {
            boolean removedPossibles = removePossibleCellValues(cellGroup);
            if(removedPossibles)
                changesWereMade = true;
        }

        return changesWereMade;
    }

    private boolean removePossibleCellValues(CellGroup cellGroup) {
        boolean foundImpossible = false;

        for (Cell cell : cellGroup.getCells()) {
            if (cell.isFilled())
                continue;

            boolean addedImpossibles = cell.addImpossibles(cellGroup.getAllValues());
            if (addedImpossibles)
                foundImpossible = true;
        }

        return foundImpossible;
    }
}
