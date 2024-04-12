package hwr.oop;

import hwr.oop.enums.Color;
import hwr.oop.pieces.*;

public class Board {
  public Piece[][] board = new Piece[8][8];

  public Board(){
    board[0][0] = new Rook(Color.WHITE, new Square(0, 0));
    board[0][1] = new Knight(Color.WHITE, new Square(0, 1));
    board[0][2] = new Bishop(Color.WHITE, new Square(0, 2));
    board[0][3] = new Queen(Color.WHITE, new Square(0, 3));
    board[0][4] = new King(Color.WHITE, new Square(0, 4));
    board[0][5] = new Bishop(Color.WHITE, new Square(0, 5));
    board[0][6] = new Knight(Color.WHITE, new Square(0, 6));
    board[0][7] = new Rook(Color.WHITE, new Square(0, 7));

    for(int i=0; i<8; i++){
      board[1][i] = new Pawn(Color.WHITE, new Square(1, i));
    }
    for(int i=0; i<8; i++){
      board[6][i] = new Pawn(Color.BLACK, new Square(6, i));
    }

    board[7][0] = new Rook(Color.BLACK, new Square(7, 0));
    board[7][1] = new Knight(Color.BLACK, new Square(7, 1));
    board[7][2] = new Bishop(Color.BLACK, new Square(7, 2));
    board[7][3] = new Queen(Color.BLACK, new Square(7, 3));
    board[7][4] = new King(Color.BLACK, new Square(7, 4));
    board[7][5] = new Bishop(Color.BLACK, new Square(7, 5));
    board[7][6] = new Knight(Color.BLACK, new Square(7, 6));
    board[7][7] = new Rook(Color.BLACK, new Square(7, 7));
  }
}
