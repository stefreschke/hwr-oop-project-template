package hwr.oop;

import hwr.oop.exceptions.ChessBoardException;
import hwr.oop.pieces.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hwr.oop.Main.convertInputToPosition;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
  private ChessBoard board;

  @BeforeEach
  void setup(){
    board = new ChessBoard();
  }

  @Test
  void testInitialBoardSetup() {
    Piece[][] actualBoard = board.getBoard();

    // Test for Rooks
    assertThat(actualBoard[0][0]).usingRecursiveComparison().isEqualTo(new Rook(Color.BLACK, new Position(0,0)));
    assertThat(actualBoard[0][7]).usingRecursiveComparison().isEqualTo(new Rook(Color.BLACK, new Position(0,7)));
    assertThat(actualBoard[7][0]).usingRecursiveComparison().isEqualTo(new Rook(Color.WHITE, new Position(7,0)));
    assertThat(actualBoard[7][7]).usingRecursiveComparison().isEqualTo(new Rook(Color.WHITE, new Position(7,7)));

    // Test for Knights
    assertThat(actualBoard[0][1]).usingRecursiveComparison().isEqualTo(new Knight(Color.BLACK, new Position(0,1)));
    assertThat(actualBoard[0][6]).usingRecursiveComparison().isEqualTo(new Knight(Color.BLACK, new Position(0,6)));
    assertThat(actualBoard[7][1]).usingRecursiveComparison().isEqualTo(new Knight(Color.WHITE, new Position(7,1)));
    assertThat(actualBoard[7][6]).usingRecursiveComparison().isEqualTo(new Knight(Color.WHITE, new Position(7,6)));

    // Test for Bishops
    assertThat(actualBoard[0][2]).usingRecursiveComparison().isEqualTo(new Bishop(Color.BLACK, new Position(0,2)));
    assertThat(actualBoard[0][5]).usingRecursiveComparison().isEqualTo(new Bishop(Color.BLACK, new Position(0,5)));
    assertThat(actualBoard[7][2]).usingRecursiveComparison().isEqualTo(new Bishop(Color.WHITE, new Position(7,2)));
    assertThat(actualBoard[7][5]).usingRecursiveComparison().isEqualTo(new Bishop(Color.WHITE, new Position(7,5)));

    // Test for Queens
    assertThat(actualBoard[0][3]).usingRecursiveComparison().isEqualTo(new Queen(Color.BLACK, new Position(0,3)));
    assertThat(actualBoard[7][3]).usingRecursiveComparison().isEqualTo(new Queen(Color.WHITE, new Position(7,3)));

    // Test for Kings
    assertThat(actualBoard[0][4]).usingRecursiveComparison().isEqualTo(new King(Color.BLACK, new Position(0,4)));
    assertThat(actualBoard[7][4]).usingRecursiveComparison().isEqualTo(new King(Color.WHITE, new Position(7,4)));

    // Test for Pawns
    for (int i = 0; i < 8; i++) {
      assertThat(actualBoard[1][i]).usingRecursiveComparison().isEqualTo(new Pawn(Color.BLACK, new Position(1,i)));
      assertThat(actualBoard[6][i]).usingRecursiveComparison().isEqualTo(new Pawn(Color.WHITE, new Position(6,i)));
    }
  }

  @Test
  void movePiece_Successful() throws ChessBoardException {
    Position from = convertInputToPosition("a2");
    Position to = convertInputToPosition("a3");
    assertTrue(board.movePiece(from, to));
    assertNull(board.getBoard()[6][0]);
    assertThat(board.getBoard()[5][0]).usingRecursiveComparison().isEqualTo(new Pawn(Color.WHITE, new Position(5,0)));
  }

  @Test
  void movePiece_Fail_outsideBoard(){
    assertFalse(board.movePiece(new Position(6, 0), new Position(-1, 0))); //a2 ->
    assertFalse(board.movePiece(new Position(6, 0), new Position(0, -1)));
    assertFalse(board.movePiece(new Position(6, 0), new Position(8, 0)));
    assertFalse(board.movePiece(new Position(6, 0), new Position(0, 8)));
    assertThat(board.getBoard()[6][0]).usingRecursiveComparison().isEqualTo(new Pawn(Color.WHITE, new Position(6,0)));

    //besser, wenn die movePiece-Funktion direkt Exceptions wirft (und zurück gibt) und nicht nur false zurück gibt
  }

//  @Test
//  void testMovePiece() {
//    Position from = new Position(1, 0);
//    Position to = new Position(3, 0);
//    assertTrue(board.movePiece(from, to));
//    assertNull(board.getBoard()[1][0]);
//    assertThat(board.getBoard()[3][0]).usingRecursiveComparison().isEqualTo(new Pawn(Color.BLACK, new Position(3, 0)));
//  }
//
//  @Test
//  void testInvalidMoveOccupied() {
//    assertFalse(board.movePiece(new Position(0, 0), new Position(1, 0)));
//    assertInstanceOf(Rook.class, board.getBoard()[0][0]);
//    assertInstanceOf(Pawn.class, board.getBoard()[1][0]);
//    //exception
//  }
//
//  @Test
//  void testInvalidMoveOutOfBounds() {
//    assertFalse(board.movePiece(new Position(1, 0), new Position(9, 0)));
//    assertInstanceOf(Pawn.class, board.getBoard()[1][0]); //Pawn stays on old position
//    //exception
//  }
//
//  @Test
//  void testInvalidMoveNoPiece() {
//    assertFalse(board.movePiece(new Position(2, 0), new Position(3, 0)));
//    assertNull(board.getBoard()[2][0]);
//    //exception
//  }
//  @Test
//  void testInvalidMoveNegativeRow() {
//    // Test for invalid move with negative row
//    assertThrows(ChessBoardException.class, () -> {
//      board.movePiece(new Position(-1, 0), new Position(3, 0));
//    });
//  }
//
//  @Test
//  void testInvalidMoveNegativeColumn() {
//    // Test for invalid move with negative column
//    assertThrows(ChessBoardException.class, () -> {
//      board.movePiece(new Position(1, -1), new Position(3, 0));
//    });
//  }
//
//  @Test
//  void testInvalidMoveRowOutOfBounds() {
//    // Test for invalid move with row out of bounds
//    assertThrows(ChessBoardException.class, () -> {
//      board.movePiece(new Position(8, 0), new Position(3, 0));
//    });
//  }
//
//  @Test
//  void testInvalidMoveColumnOutOfBounds() {
//    // Test for invalid move with column out of bounds
//    assertThrows(ChessBoardException.class, () -> {
//      board.movePiece(new Position(1, 8), new Position(3, 0));
//    });
//  }
//
//  @Test
//  void testInvalidMoveBothOutOfBounds() {
//    // Test for invalid move with both row and column out of bounds
//    assertThrows(ChessBoardException.class, () -> {
//      board.movePiece(new Position(-1, 8), new Position(3, 0));
//    });
//  }
}