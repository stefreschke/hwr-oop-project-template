package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

class SudokuTest {
    private final int[][] unsolvedSudokuArray = {
            {0,0,0, 9,0,0, 4,0,0},
            {5,0,6, 0,0,0, 0,0,0},
            {9,0,0, 3,0,0, 6,8,1},

            {0,2,0, 0,5,0, 1,0,0},
            {0,8,0, 0,7,9, 0,0,0},
            {6,0,7, 0,0,8, 0,0,0},

            {0,0,0, 0,2,0, 0,4,0},
            {0,0,0, 0,9,0, 0,5,0},
            {0,5,0, 0,0,0, 0,0,2},
    };
    private final Sudoku unsolvedSudoku = new Sudoku(unsolvedSudokuArray);


    @Test
    void sudoku_constructorFillsCellsCorrectly() {
        for (int rowIndex = 0; rowIndex < unsolvedSudokuArray.length; rowIndex ++) {
            for (int columnIndex = 0; columnIndex < unsolvedSudokuArray[0].length; columnIndex++) {
                if (unsolvedSudoku.getCellAt(rowIndex, columnIndex).getValue() != unsolvedSudokuArray[rowIndex][columnIndex]) {
                    fail();
                }
            }
        }
    }

    @Test
    void sudoku_getsFirstRow() {
        int[] firstRowValues = {0, 0, 0, 9, 0, 0, 4, 0, 0};
        CellGroup row = unsolvedSudoku.getRow(0);
        for (int i = 0; i < 9; i++) {
            if (row.getCells().get(i).getValue() != firstRowValues[i]) {
                fail();
            }
        }
    }

    @Test
    void sudoku_getsFirstColumn() {
        int[] firstColumnValues = {0, 5, 9, 0, 0, 6, 0, 0, 0};
        CellGroup row = unsolvedSudoku.getColumn(0);
        for (int i = 0; i < 9; i++) {
            if (row.getCells().get(i).getValue() != firstColumnValues[i]) {
                fail();
            }
        }
    }

    @Test
    void sudoku_getsFirstBox() {
        List<Integer> firstBoxValues = List.of(0, 0, 0, 5, 0, 6, 9, 0, 0);
        CellGroup box = unsolvedSudoku.getBox(0);
        if (!Arrays.equals(box.getCells().stream().map(Cell::getValue).toArray(), firstBoxValues.toArray())) {
            fail();
        }
    }

    @Test
    void sudoku_getsFirstRowValues() {
        Set<Integer> expectedValues = Set.of(9, 4);
        if (!unsolvedSudoku.getRow(0).getAllValues().equals(expectedValues)) {
            fail();
        }
    }

    @Test
    void sudoku_getsFirstColumnValues() {
        Set<Integer> expectedValues = Set.of(5, 9, 6);
        System.out.println(Arrays.toString(unsolvedSudoku.getRow(0).getAllValues().toArray()));
        if (!unsolvedSudoku.getColumn(0).getAllValues().equals(expectedValues)) {
            fail();
        }
    }

    @Test
    void sudoku_getsFirstBoxValues() {
        Set<Integer> expectedValues = Set.of(5, 9, 6);
        if (!unsolvedSudoku.getBox(0).getAllValues().equals(expectedValues)) {
            fail();
        }
    }
}
