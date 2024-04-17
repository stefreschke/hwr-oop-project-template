package hwr.oop;

import hwr.oop.exceptions.ChessBoardException;
import hwr.oop.exceptions.MovePieceException;
import hwr.oop.pieces.*;

public class ChessBoard {
  private final Piece[][] board;

  public ChessBoard() {
    this.board = new Piece[8][8]; // 8x8 chessboard
    setupPieces();
  }

  private void setupPieces() {
    board[0][0] = new Rook(Color.BLACK, new Position(0, 0));
    board[0][7] = new Rook(Color.BLACK, new Position(0, 7));
    board[7][0] = new Rook(Color.WHITE, new Position(7, 0));
    board[7][7] = new Rook(Color.WHITE, new Position(7, 7));
    // Place Knights
    board[0][1] = new Knight(Color.BLACK, new Position(0, 1));
    board[0][6] = new Knight(Color.BLACK, new Position(0, 6));
    board[7][1] = new Knight(Color.WHITE, new Position(7, 1));
    board[7][6] = new Knight(Color.WHITE, new Position(7, 6));
    // Place Bishops
    board[0][2] = new Bishop(Color.BLACK, new Position(0, 2));
    board[0][5] = new Bishop(Color.BLACK, new Position(0, 5));
    board[7][2] = new Bishop(Color.WHITE, new Position(7, 2));
    board[7][5] = new Bishop(Color.WHITE, new Position(7, 5));
    // Place Queens
    board[0][3] = new Queen(Color.BLACK, new Position(0, 3));
    board[7][3] = new Queen(Color.WHITE, new Position(7, 3));
    // Place Kings
    board[0][4] = new King(Color.BLACK, new Position(0, 4));
    board[7][4] = new King(Color.WHITE, new Position(7, 4));
    // Place Pawns
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(Color.BLACK, new Position(1, i));
      board[6][i] = new Pawn(Color.WHITE, new Position(6, i));
    }
  }

  public Piece[][] getBoard() {
    return board;
  }

  public static void printChessBoard(Piece[][] board) {
    System.out.println("   a b c d e f g h");
    System.out.println(" +-----------------+");
    for (int i = 0; i < 8; i++) {
      System.out.print(8 - i + "| ");
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null) {
          System.out.print(board[i][j].getSymbol() + " ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println("|");
    }

    System.out.println(" +-----------------+");
  }

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

      Piece piece = board[from.row()][from.column()];
      if (piece == null) {
        throw new MovePieceException("No piece at the specified position!");
      }

      if (to.row() < 0 || to.row() >= 8 || to.column() < 0 || to.column() >= 8) {
        throw new MovePieceException("Invalid destination position!");
      }

      if (board[to.row()][to.column()] != null
          && board[to.row()][to.column()].getColor() == piece.getColor()) {
        throw new MovePieceException("Destination position occupied by own piece!");
      }

      board[to.row()][to.column()] = piece;
      board[from.row()][from.column()] = null;
      piece.setPosition(to);
      return true;
  }
}
