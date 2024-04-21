package hwr.oop;

import hwr.oop.exceptions.ChessBoardException;
import hwr.oop.exceptions.MovePieceException;
import hwr.oop.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ChessBoard {
  private final List<List<Piece>> board = new ArrayList<>();

  public ChessBoard() {
    IntStream.range(0, 8).forEach(i -> {
      List<Piece> row = new ArrayList<>();
      IntStream.range(0, 8).forEach(j -> row.add(null));
      board.add(row);
    });
    setupPieces();
  }


  private void setupPieces() {
    // Place Rooks
    board.get(0).set(0, new Rook(Color.BLACK, new Position(0, 0)));
    board.get(0).set(7, new Rook(Color.BLACK, new Position(0, 7)));
    board.get(7).set(0, new Rook(Color.WHITE, new Position(7, 0)));
    board.get(7).set(7, new Rook(Color.WHITE, new Position(7, 7)));
    // Place Knights
    board.get(0).set(1, new Knight(Color.BLACK, new Position(0, 1)));
    board.get(0).set(6, new Knight(Color.BLACK, new Position(0, 6)));
    board.get(7).set(1, new Knight(Color.WHITE, new Position(7, 1)));
    board.get(7).set(6, new Knight(Color.WHITE, new Position(7, 6)));
    // Place Bishops
    board.get(0).set(2, new Bishop(Color.BLACK, new Position(0, 2)));
    board.get(0).set(5, new Bishop(Color.BLACK, new Position(0, 5)));
    board.get(7).set(2, new Bishop(Color.WHITE, new Position(7, 2)));
    board.get(7).set(5, new Bishop(Color.WHITE, new Position(7, 5)));
    // Place Queens
    board.get(0).set(3, new Queen(Color.BLACK, new Position(0, 3)));
    board.get(7).set(3, new Queen(Color.WHITE, new Position(7, 3)));
    // Place Kings
    board.get(0).set(4, new King(Color.BLACK, new Position(0, 4)));
    board.get(7).set(4, new King(Color.WHITE, new Position(7, 4)));
    // Place Pawns
    for (int i = 0; i < 8; i++) {
      board.get(1).set(i, new Pawn(Color.BLACK, new Position(1, i)));
      board.get(6).set(i, new Pawn(Color.WHITE, new Position(6, i)));
    }
  }

  public List<List<Piece>> getBoard() {
    return board;
  }

//  public static void printChessBoard(List<List<Piece>> board) {
//    System.out.println("   a b c d e f g h");
//    System.out.println(" +-----------------+");
//    for (int i = 0; i < 8; i++) {
//      System.out.print(8 - i + "| ");
//      for (int j = 0; j < 8; j++) {
//        Piece piece = board.get(i).get(j);
//        if (piece != null) {
//          System.out.print(piece.getSymbol() + " ");
//        } else {
//          System.out.print(". ");
//        }
//      }
//      System.out.println("|");
//    }
//    System.out.println(" +-----------------+");
//  }

  public static Position convertInputToPosition(String input) throws ChessBoardException {
    if (input.length() != 2 || !Character.isLetter(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
      throw new ChessBoardException("Invalid input format. Please provide a valid position (e.g., 'a1').");
    }
    int column = input.charAt(0) - 'a';
    int row = 8 - Character.getNumericValue(input.charAt(1));

    if (column < 0 || column >= 8 || row < 0 || row >= 8) {
      throw new ChessBoardException("Invalid position. Position must be within the chessboard.");
    }

    return new Position(row, column);
  }

  public boolean movePiece(Position from, Position to) throws MovePieceException {
    Piece piece = board.get(from.row()).get(from.column());
    if (piece == null) {
      throw new MovePieceException("No piece at the specified position!");
    }

    if (to.row() < 0 || to.row() >= 8 || to.column() < 0 || to.column() >= 8) {
      throw new MovePieceException("Invalid destination position!");
    }

    if (board.get(to.row()).get(to.column()) != null && board.get(to.row()).get(to.column()).getColor() == piece.getColor()) {
      throw new MovePieceException("Destination position occupied by own piece!");
    }

    board.get(to.row()).set(to.column(), piece);
    board.get(from.row()).set(from.column(), null);
    piece.setPosition(to);
    return true;
  }
}
