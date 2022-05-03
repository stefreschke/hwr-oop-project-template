package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.solver.component.*;
import hwr.oop.riddler.model.Sudoku;

public class SudokuSolver {

    public Sudoku solve(Sudoku sudoku) {
        Sudoku solution;
        try {
            solution = solveWithSteps(sudoku);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            solution = null;
        }

        return solution;
    }

    private SolvingComponent[] getSolvingComponents(Sudoku sudoku) {
        return new SolvingComponent[]{
                new PossiblesEliminator(sudoku),
                //new AdvancedPossiblesEliminator(), -- TODO: work on this once solver works
                new SinglePossibleImplementor(sudoku),
                new Backtracker(sudoku)
        };
    }

    private Sudoku solveWithSteps(Sudoku sudoku) {
        var solvingComponents = getSolvingComponents(sudoku);

        for (SolvingComponent solvingComponent : solvingComponents) {
            //System.out.println("step " + solvingStepIndex);
            boolean solvingStepMadeChanges = solvingComponent.execute();
            if (solvingStepMadeChanges) {
                solveWithSteps(sudoku);
                break;
            }
        }
        return sudoku;
    }
}
