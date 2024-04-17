package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
  private ArrayList<ArrayList<Piece>> board;

  public Board() {
    board = new ArrayList<>(8);
    for (ArrayList<Piece> row : board) {
      row = new ArrayList<>(8);
    }
  }

  public void initBoard() {
    for (ArrayList<Piece> row : board) {
      for (Piece i : row) {
        i = null;
      }
    }

    this.board
        .getFirst()
        .set(0, new Piece(Piece.PieceType.TURM, Arrays.asList(0, 0), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(1, new Piece(Piece.PieceType.SPRINGER, Arrays.asList(0, 1), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(2, new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 2), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(3, new Piece(Piece.PieceType.DAME, Arrays.asList(0, 3), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(4, new Piece(Piece.PieceType.KOENIG, Arrays.asList(0, 4), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(5, new Piece(Piece.PieceType.LAEUFER, Arrays.asList(0, 5), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(6, new Piece(Piece.PieceType.SPRINGER, Arrays.asList(0, 6), Piece.Color.WHITE));
    this.board
        .getFirst()
        .set(7, new Piece(Piece.PieceType.TURM, Arrays.asList(0, 7), Piece.Color.WHITE));

    for (int i = 0; i < 8; i++) {
      this.board
          .get(1)
          .set(i, new Piece(Piece.PieceType.BAUER, Arrays.asList(1, i), Piece.Color.WHITE));
    }

    this.board
        .get(7)
        .set(0, new Piece(Piece.PieceType.TURM, Arrays.asList(7, 0), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(1, new Piece(Piece.PieceType.SPRINGER, Arrays.asList(7, 1), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(2, new Piece(Piece.PieceType.LAEUFER, Arrays.asList(7, 2), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(3, new Piece(Piece.PieceType.DAME, Arrays.asList(7, 3), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(4, new Piece(Piece.PieceType.KOENIG, Arrays.asList(7, 4), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(5, new Piece(Piece.PieceType.LAEUFER, Arrays.asList(7, 5), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(6, new Piece(Piece.PieceType.SPRINGER, Arrays.asList(7, 6), Piece.Color.BLACK));
    this.board
        .get(7)
        .set(7, new Piece(Piece.PieceType.TURM, Arrays.asList(7, 7), Piece.Color.BLACK));

    for (int i = 0; i < 8; i++) {
      this.board
          .get(7)
          .set(i, new Piece(Piece.PieceType.BAUER, Arrays.asList(6, i), Piece.Color.BLACK));
    }
  }

  public void changePos(int oldX, int oldY, int newX, int newY) {
    this.board.get(newX).set(newY, board.get(oldX).get(oldY));
    this.board.get(oldX).set(oldY, null);
  }

  public Piece getPieceAt(int x, int y) {
    return this.board.get(x).get(y);
  }

  public ArrayList<ArrayList<Piece>> getBoard() {
    return board;
  }

  public void printBoard() {
    System.out.println("Printing Board here");
  }

  public boolean isValidMove(Piece piece, int x, int y) {
    int vecX = x - piece.getActPosition().get(0);
    int vecY = y - piece.getActPosition().get(1);
    for (List<Integer> i : piece.getPosMoves()) {
      if (piece.isMoveRepeatable()) {
        for (int j = -7; j < 8; j++) {
          if (j == 0) {
            continue;
          }
          if (i.getFirst() * j == vecX && i.get(1) * j == vecY) {
            return true;
          }
        }
      } else {
        if (i.getFirst() == vecX && i.get(1) == vecY) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isBlocked(Piece piece, int newX, int newY) {
    List<Integer> oldPos = piece.getActPosition();
    List<Integer> vec = Arrays.asList(newX - oldPos.getFirst(), newY - oldPos.get(1));
    if (vec.getFirst() != 0) {
      if (vec.getFirst() < 0) {
        vec.set(0, -1);
      } else {
        vec.set(0, 1);
      }
    }
    if (vec.get(1) != 0) {
      if (vec.get(1) < 0) {
        vec.set(1, -1);
      } else {
        vec.set(1, 1);
      }
    }
    for (int i = 1;
        i < ((newX - oldPos.getFirst()) * vec.getFirst())
            || i < ((newY - oldPos.get(1)) * vec.get(1));
        i++) {
      if (this.board.get(oldPos.getFirst() + i * vec.getFirst()).get(oldPos.get(1) + i * vec.get(1))
          != null) {
        return true;
      }
    }
    return false;
  }
}
