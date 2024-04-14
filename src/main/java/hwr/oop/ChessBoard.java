package hwr.oop;

import hwr.oop.exceptions.ChessBoardException;
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

  public boolean movePiece(Position from, Position to) {
    try {
      Piece piece = board[from.row()][from.column()];
      if (piece == null) {
        throw new ChessBoardException("No piece at the specified position!");
      }

      // Check if the destination is outside the board
      if (to.row() < 0 || to.row() >= 8 || to.column() < 0 || to.column() >= 8) {
        throw new ChessBoardException("Invalid destination position!");
      }

      // Check if the move is valid (specific rules need to be implemented here)
      // Here, just a simple check if the destination is empty or contains an opponent's piece
      if (board[to.row()][to.column()] != null
          && board[to.row()][to.column()].getColor() == piece.getColor()) {
        throw new ChessBoardException("Destination position occupied by own piece!");
      }

      // Perform the move
      board[to.row()][to.column()] = piece;
      board[from.row()][from.column()] = null;
      piece.setPosition(to);
      return true;
    } catch (ChessBoardException e) {
      System.out.println("Error while executing the move: " + e.getMessage());
      return false;
    }
  }
}
