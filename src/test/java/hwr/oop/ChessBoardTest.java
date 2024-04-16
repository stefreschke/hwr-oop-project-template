package hwr.oop;

import hwr.oop.exceptions.ChessBoardException;
import hwr.oop.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static hwr.oop.ChessBoard.convertInputToPosition;
import static hwr.oop.ChessBoard.printChessBoard;
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
  void testPrintChessBoard() {
    ChessBoard chessBoard = new ChessBoard();
    printChessBoard(chessBoard.getBoard());

    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStreamCaptor));

    printChessBoard(chessBoard.getBoard());

    String expectedOutput =
            """
                      a b c d e f g h\r
                     +-----------------+\r
                    8| r n b q k b n r |\r
                    7| p p p p p p p p |\r
                    6| . . . . . . . . |\r
                    5| . . . . . . . . |\r
                    4| . . . . . . . . |\r
                    3| . . . . . . . . |\r
                    2| P P P P P P P P |\r
                    1| R N B Q K B N R |\r
                     +-----------------+\r
                      a b c d e f g h\r
                    """;

    assertThat(outputStreamCaptor.toString()).hasToString(expectedOutput);
  }

  @Test
  void convertInputToPosition_Valid() throws ChessBoardException {
    assertThat(convertInputToPosition("a8")).isEqualTo(new Position(0, 0));
    assertThat(convertInputToPosition("h1")).isEqualTo(new Position(7, 7));
    assertThat(convertInputToPosition("e5")).isEqualTo(new Position(3, 4));
  }

  @Test
  void convertInputToPosition_InvalidFormat() {
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("a"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("abc"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("a12"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("12"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("abc12"));
  }

  @Test
  void testInvalidPosition() {
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("i1"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("a0"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("a9"));
    assertThrows(ChessBoardException.class, () -> convertInputToPosition("h9"));
  }

  @Test
  void movePiece_Successful() throws ChessBoardException {
    Position from = convertInputToPosition("a2");
    Position to = convertInputToPosition("a3");
    assertThat(board.movePiece(from, to)).isTrue();
    assertThat(board.getBoard()[6][0]).isNull();
    assertThat(board.getBoard()[5][0]).usingRecursiveComparison().isEqualTo(new Pawn(Color.WHITE, new Position(5,0)));
  }

  @Test
  void movePiece_Fail_outsideBoard(){
    assertThat(board.movePiece(new Position(6, 0), new Position(-1, 0))).isFalse();
    assertThat(board.movePiece(new Position(6, 0), new Position(0, -1))).isFalse();
    assertThat(board.movePiece(new Position(6, 0), new Position(8, 0))).isFalse();
    assertThat(board.movePiece(new Position(6, 0), new Position(0, 8))).isFalse();
    assertThat(board.movePiece(new Position(6, 0), new Position(9, 9))).isFalse();
    assertThat(board.movePiece(new Position(6, 0), new Position(0, 9))).isFalse();

    assertThat(board.getBoard()[6][0]).usingRecursiveComparison().isEqualTo(new Pawn(Color.WHITE, new Position(6,0)));
  }

  @Test
  void movePiece_Fail_OccupiedPositionByOwnPiece(){
    Position from = new Position(7,0); //a1
    Position to = new Position(6,0); //a2
    assertThat(board.movePiece(from, to)).isFalse();

  }

  @Test
  void movePiece_Fail_NoPieceOnFromPosition(){
    Position from = new Position(3,3); //d5
    Position to = new Position(4,3); //d4
    assertThat(board.movePiece(from,to)).isFalse();
  }


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