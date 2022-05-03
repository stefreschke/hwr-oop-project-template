package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;

public abstract class SolvingComponent {
    protected Sudoku sudoku;
    protected SolvingComponent(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
    public abstract boolean execute();
}
