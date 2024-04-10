package hwr.oop;

import hwr.oop.pieces.*;

public class ChessBoard {
  private Piece[][] board;

  public ChessBoard(){
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
    Piece piece = board[from.getRow()][from.getColumn()];
    if (piece == null) {
      System.out.println("Keine Figur an der angegebenen Position!");
      return false;
    }

    // Überprüfen, ob das Ziel außerhalb des Bretts liegt
    if (to.getRow() < 0 || to.getRow() >= 8 || to.getColumn() < 0 || to.getColumn() >= 8) {
      System.out.println("Ungültige Zielposition!");
      return false;
    }

    // Überprüfen, ob die Bewegung gültig ist (hier müssen die spezifischen Regeln implementiert werden)
    // Hier nur eine einfache Überprüfung, ob das Ziel leer ist oder eine gegnerische Figur enthält
    if (board[to.getRow()][to.getColumn()] != null && board[to.getRow()][to.getColumn()].getColor() == piece.getColor()) {
      System.out.println("Zielposition von eigener Figur besetzt!");
      return false;
    }

    // Bewegung durchführen
    board[to.getRow()][to.getColumn()] = piece;
    board[from.getRow()][from.getColumn()] = null;
    piece.setPosition(to);
    return true;
  }
}
