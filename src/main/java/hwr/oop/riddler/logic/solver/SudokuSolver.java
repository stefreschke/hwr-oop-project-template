package hwr.oop.riddler.logic.solver;

import hwr.oop.riddler.logic.solver.component.*;
import hwr.oop.riddler.model.Sudoku;

public class SudokuSolver {
    IterativeSudokuSolver[] solvingComponents;

    public SudokuSolver() {
        solvingComponents = new IterativeSudokuSolver[]{
                new PossiblesEliminator(),
                new AdvancedPossiblesEliminator(),
                new SinglePossibleImplementor(),
                new Backtracker()
        };
    }

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

    private Sudoku solveWithSteps(Sudoku sudoku) {
        for (IterativeSudokuSolver solvingComponent : solvingComponents) {
            //System.out.println("step " + solvingStepIndex);
            boolean solvingStepMadeChanges = solvingComponent.doSolvingStep(sudoku);
            if (solvingStepMadeChanges) {
                solveWithSteps(sudoku);
                break;
            }
        }
        return sudoku;
    }
}
