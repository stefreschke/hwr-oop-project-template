package hwr.oop;

import static hwr.oop.Main.printChessBoard;
import static hwr.oop.Main.convertInputToPosition;
import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.exceptions.ChessBoardException;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

  @Test
  void testPrintChessBoard() {
    ChessBoard chessBoard = new ChessBoard();
    printChessBoard(chessBoard.getBoard());

    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStreamCaptor));

    printChessBoard(chessBoard.getBoard());

    String expectedOutput =
        "  a b c d e f g h\r\n"
            + " +-----------------+\r\n"
            + "8| r n b q k b n r |\r\n"
            + "7| p p p p p p p p |\r\n"
            + "6| . . . . . . . . |\r\n"
            + "5| . . . . . . . . |\r\n"
            + "4| . . . . . . . . |\r\n"
            + "3| . . . . . . . . |\r\n"
            + "2| P P P P P P P P |\r\n"
            + "1| R N B Q K B N R |\r\n"
            + " +-----------------+\r\n"
            + "  a b c d e f g h\r\n";

    assertEquals(expectedOutput, outputStreamCaptor.toString());
  }

  @Test
  void convertInputToPosition_Valid() throws ChessBoardException {
    assertEquals(new Position(0, 0), convertInputToPosition("a8"));
    assertEquals(new Position(7, 7), convertInputToPosition("h1"));
    assertEquals(new Position(3, 4), convertInputToPosition("e5"));
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
    assertThrows(ChessBoardException.class, () -> Main.convertInputToPosition("i1"));
    assertThrows(ChessBoardException.class, () -> Main.convertInputToPosition("a0"));
    assertThrows(ChessBoardException.class, () -> Main.convertInputToPosition("a9"));
    assertThrows(ChessBoardException.class, () -> Main.convertInputToPosition("h9"));
  }
}
