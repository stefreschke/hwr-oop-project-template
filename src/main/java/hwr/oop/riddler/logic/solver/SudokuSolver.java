package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.solver.component.*;
import hwr.oop.riddler.model.Sudoku;

public class SudokuSolver {
    SolvingComponent[] solvingComponents;

    public void solve(Sudoku sudoku) {
        solvingComponents = getSolvingComponents(sudoku);
        try {
            boolean changesWereMade = true;
            while (changesWereMade) {
                changesWereMade = solveWithSteps();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private SolvingComponent[] getSolvingComponents(Sudoku sudoku) {
        return new SolvingComponent[]{
                new PossiblesEliminator(sudoku),
                //new AdvancedPossiblesEliminator(), -- TODO: work on this once solver works
                new SinglePossibleImplementor(sudoku),
                new Backtracker(sudoku)
        };
    }

    private boolean solveWithSteps() {
        for (SolvingComponent solvingComponent : solvingComponents) {
            boolean solvingStepMadeChanges = solvingComponent.execute();
            if (solvingStepMadeChanges) {
                return true;
            }
        }
        return false;
    }
}
