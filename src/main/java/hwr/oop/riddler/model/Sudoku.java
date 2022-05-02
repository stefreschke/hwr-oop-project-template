package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;

import java.util.*;
import java.util.function.IntFunction;

public class Sudoku {
    private Cell[][] cells;
    private final int size;

    public Sudoku(int[][] input) {
        size = input.length;
        cells = new Cell[size][size];
        fillCells(input);
    }

    private void fillCells(int[][] input) {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            int[] row = input[rowIndex];
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                int cellValue = row[columnIndex];
                Cell cell;
                if (cellValue != 0) {
                    cell = new Cell(cellValue);
                } else {
                    cell = new Cell();
                }
                cells[rowIndex][columnIndex] = cell;
            }
        }
    }

    public List<Cell> getAllCells() {
        var collectedCells = new ArrayList<Cell>();
        for (Cell[] rows : cells) {
            Collections.addAll(collectedCells, rows);
        }
        return collectedCells;
    }

    public CellGroup getRow(int rowIndex) {
        return new CellGroup(List.of(cells[rowIndex]));
    }

    public CellGroup getColumn(int columnIndex) {
        var column = new ArrayList<Cell>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            column.add(this.cells[rowIndex][columnIndex]);
        }
        return new CellGroup(column);
    }

    public void print() {
        for (Cell[] rows : cells) {
            for (Cell cell : rows) {
                System.out.print(cell.getValue() + " ");
            }
            System.out.println();
        }
    }

    public CellGroup getBox(int boxIndex) {
        var boxCells = new ArrayList<Cell>(9);
        int boxLatitude = boxIndex / 3;
        int boxLongitude = boxIndex % 3;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                boxCells.add(cells[boxLatitude * 3 + y][boxLongitude * 3 + x]);
            }
        }
        return new CellGroup(boxCells);
    }

    public Set<CellGroup> getRows() {
        return getGroup(this::getRow);
    }

    public Set<CellGroup> getColumns() {
        return getGroup(this::getColumn);
    }

    public Set<CellGroup> getBoxes() {
        return getGroup(this::getBox);
    }

    private Set<CellGroup> getGroup(IntFunction<CellGroup> function) {
        var group = new HashSet<CellGroup>(9);
        for (int i = 0; i < size; i++) {
            group.add(function.apply(i));
        }
        return group;
    }

    public Cell getCellAt(int row, int column) {
        return cells[row][column];
    }
}
