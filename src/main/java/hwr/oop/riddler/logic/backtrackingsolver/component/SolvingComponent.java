package hwr.oop.riddler.logic.backtrackingsolver.component;

import hwr.oop.riddler.model.Sudoku;

public interface SolvingComponent {
    boolean execute(Sudoku sudoku);
}
