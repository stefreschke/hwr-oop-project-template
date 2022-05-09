package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.*;
import lombok.Getter;

import java.util.*;
import java.util.function.IntFunction;

public class Sudoku {
    private final Cell[][] cells;
    @Getter
    private final int size;
    private final int boxSize;

    public Sudoku(int[][] input) {
        size = input.length;
        boxSize = (int) Math.sqrt(size);
        cells = new Cell[size][size];
        fillCells(input);
    }

    public Sudoku(Sudoku sudoku) {
        this.cells = new Cell[sudoku.size][sudoku.size];
        for (int rowIndex = 0; rowIndex < sudoku.size; rowIndex++) {
            for (int columnIndex = 0; columnIndex < sudoku.size; columnIndex++) {
                this.cells[rowIndex][columnIndex] = new Cell(sudoku.cells[rowIndex][columnIndex]);
            }
        }
        this.size = sudoku.size;
        this.boxSize = sudoku.boxSize;
    }

    private void fillCells(int[][] input) {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            int[] row = input[rowIndex];
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                int cellValue = row[columnIndex];
                cells[rowIndex][columnIndex] = new Cell(cellValue);
            }
        }
    }

    public int[][] getValues() {
        int[][] sudoku = new int[size][size];
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                Cell cell = cells[rowIndex][columnIndex];
                sudoku[rowIndex][columnIndex] = cell.isFilled() ? cell.getValue() : 0;
            }
        }
        return sudoku;
    }

    public List<Cell> getCells() {
        var collectedCells = new ArrayList<Cell>();
        for (Cell[] rows : cells) {
            Collections.addAll(collectedCells, rows);
        }
        return collectedCells;
    }

    public List<Cell> getUnsolvedCells() {
        return getCells().stream().filter(Cell::isEmpty).toList();
    }

    public CellGroup getRow(int rowIndex) {
        return new CellGroup(Set.of(cells[rowIndex]));
    }

    public CellGroup getColumn(int columnIndex) {
        var column = new HashSet<Cell>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            column.add(this.cells[rowIndex][columnIndex]);
        }
        return new CellGroup(column);
    }

    public CellGroup getBox(int boxIndex) {
        var boxCells = new HashSet<Cell>(9);
        int boxLatitude = boxIndex / boxSize;
        int boxLongitude = boxIndex % boxSize;
        for (int y = 0; y < boxSize; y++) {
            for (int x = 0; x < boxSize; x++) {
                boxCells.add(cells[boxLatitude * boxSize + y][boxLongitude * boxSize + x]);
            }
        }
        return new CellGroup(boxCells);
    }

    public Set<CellGroup> getRows() {
        return getAllGroupsOfSameType(this::getRow);
    }

    public Set<CellGroup> getColumns() {
        return getAllGroupsOfSameType(this::getColumn);
    }

    public Set<CellGroup> getBoxes() {
        return getAllGroupsOfSameType(this::getBox);
    }

    private Set<CellGroup> getAllGroupsOfSameType(IntFunction<CellGroup> getGroup) {
        var groups = new HashSet<CellGroup>(size);
        for (int i = 0; i < size; i++) {
            groups.add(getGroup.apply(i));
        }
        return groups;
    }

    public Set<CellGroup> getConcatenatedCellGroups() {
        var allCellGroups = new HashSet<CellGroup>(size * 3);
        allCellGroups.addAll(getRows());
        allCellGroups.addAll(getColumns());
        allCellGroups.addAll(getBoxes());
        return allCellGroups;
    }

    public Cell getCellAt(int row, int column) {
        return cells[row][column];
    }

    public boolean isFilled() {
        return getUnsolvedCells().isEmpty();
    }
}
