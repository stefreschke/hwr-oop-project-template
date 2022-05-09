package hwr.oop.riddler.model;

import hwr.oop.riddler.model.component.Cell;
import hwr.oop.riddler.model.component.CellGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    Sudoku unsolved;
    Sudoku solved;
    Sudoku unsolvedNineByNine;
    private final int[][] unsolvedNineByNineArray = {
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

    private final int[][] unsolvedFourByFourArray = {
            {0, 1, 0, 3},
            {3, 0, 1, 0},
            {0, 3, 0, 1},
            {0, 4, 0, 2},
    };

    private final int[][] solvedFourByFourArray = {
            {4, 1, 2, 3},
            {3, 2, 1, 4},
            {2, 3, 4, 1},
            {1, 4, 3, 2},
    };

    @BeforeEach
    void setup() {
        unsolved = new Sudoku(unsolvedFourByFourArray);
        solved = new Sudoku(solvedFourByFourArray);
        unsolvedNineByNine = new Sudoku(unsolvedNineByNineArray);
    }

    @Test
    void unsolved_isFilledIsFalse() {
        assertFalse(unsolved.isFilled());
    }

    @Test
    void solved_isFilled() {
        assertTrue(solved.isFilled());
    }

    @Test
    void unsolvedNineByNine_isFilledIsFalse() {
        assertFalse(unsolvedNineByNine.isFilled());
    }
}
