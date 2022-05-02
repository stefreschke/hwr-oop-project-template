package hwr.oop.riddler.logic.solver.component;

import hwr.oop.riddler.model.Sudoku;
import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

import java.util.HashSet;
import java.util.Set;

public class AdvancedPossiblesEliminator implements IterativeSudokuSolver {
    @Override
    public boolean doSolvingStep(Sudoku sudoku) {
        for (CellGroup box : sudoku.getBoxes()) {
            for (int i = 1; i <= 9; i++) {
                Set<CellGroup> rows = new HashSet<>();
                for (Cell cell : box.getCells()) {
                    if (!cell.isFilled() && cell.getPossibles().contains(i)) {
                        rows.add(getRow(cell, sudoku));
                    }
                }
                if (removePossibleInBoxByGroupLine(sudoku, box, i, rows)) return true;


                Set<CellGroup> cols = new HashSet<>();
                for (Cell cell : box.getCells()) {
                    if (!cell.isFilled() && cell.getPossibles().contains(i)) {
                        cols.add(getCol(cell, sudoku));
                    }
                }
                if (removePossibleInBoxByGroupLine(sudoku, box, i, cols)) return true;
            }
        }
        return false;
    }

    private boolean removePossibleInBoxByGroupLine(Sudoku sudoku, CellGroup box, int i, Set<CellGroup> groups) {
        if (groups.size() == 1) {
            CellGroup group = groups.iterator().next();
            for (Cell cell : group.getCells()) {
                if (!cell.isFilled() && !getBoxOfCell(cell, sudoku).equals(box) && cell.getPossibles().contains(i)) {
                    cell.removePossible(i);
                    return true;
                }
            }
        } else if (groups.size() == 3) {
            if (doStuff(sudoku, box, i, groups)) return true;
        }
        return false;
    }

    private boolean doStuff(Sudoku sudoku, CellGroup box, int i, Set<CellGroup> rows) {
        //System.out.println("Do stuff in box " + Arrays.toString(box.getAllValues().toArray()));
        Set<Integer> indices = new HashSet<>();
        int level = 0;
        for (CellGroup row : rows) {
            level++;
            for (Cell cell : row.getCells()) {
                if (!cell.isFilled() && cell.getPossibles().contains(i) && !getBoxOfCell(cell, sudoku).equals(box)) {
                    //System.out.println("Found " + i + " in another box");
                    indices.add(level);
                }
            }
        }
        level = 0;
        if (indices.size() == 2) {
            for (CellGroup row : rows) {
                level++;
                for (Cell cell : row.getCells()) {
                    if (!cell.isFilled() && cell.getPossibles().contains(i) && getBoxOfCell(cell, sudoku).equals(box) && indices.contains(level)) {
                        cell.removePossible(i);
                        System.out.println("REMOVED POSSBILE");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public CellGroup getBoxOfCell(Cell cell, Sudoku sudoku) {
        for (CellGroup box : sudoku.getBoxes()) {
            if (box.getCells().contains(cell)) return box;
        }
        return null;
    }

    public CellGroup getRow(Cell cell, Sudoku sudoku) {
        for (CellGroup box : sudoku.getRows()) {
            if (box.getCells().contains(cell)) return box;
        }
        return null;
    }

    public CellGroup getCol(Cell cell, Sudoku sudoku) {
        for (CellGroup box : sudoku.getColumns()) {
            if (box.getCells().contains(cell)) return box;
        }
        return null;
    }
}
