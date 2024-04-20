package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
  private ArrayList<ArrayList<Piece>> board;

  private Map<Character, Piece.PieceType> charToPieceType;

  public void CharToPieceType() {
    charToPieceType = new HashMap<>();
    charToPieceType.put('r', Piece.PieceType.TURM);
    charToPieceType.put('n', Piece.PieceType.SPRINGER);
    charToPieceType.put('b', Piece.PieceType.LAEUFER);
    charToPieceType.put('q', Piece.PieceType.DAME);
    charToPieceType.put('k', Piece.PieceType.KOENIG);
    charToPieceType.put('p', Piece.PieceType.BAUER);
  }

  public Board() {
    CharToPieceType();
    //    board = new ArrayList<>(8);
    //    for (ArrayList<Piece> row : board) {
    //      row = new ArrayList<>(8);
    //    }
    board = new ArrayList<>(8);

    for (int i = 0; i < 8; i++) {
      board.add(new ArrayList<>(8));
    }

    for (ArrayList<Piece> row : board) {
      for (int i = 0; i < 8; i++) {
        row.add(null);
      }
    }
  }

  public void initBoard() {
    setBoardToFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
  }

  public void changePos(int oldX, int oldY, int newX, int newY) {
    this.board.get(newX).set(newY, board.get(oldX).get(oldY));
    this.board.get(oldX).set(oldY, null);
  }

  public Piece getPieceAt(int row, int column) {
    return this.board.get(row).get(column);
  }

  private void setPieceAt(int row, int column, Piece piece) {
    this.board.get(row).set(column, piece);
  }

  public ArrayList<ArrayList<Piece>> getBoard() {
    return board;
  }

  public void printBoard() {
    System.out.println("Printing Board here");
  }

  public void setBoardToFen(String fen) {

    int row = 7;
    int column = 0;

    for (char c : fen.toCharArray()) {

      if (c == '/') {
        row--;
        column = 0;
      } else if (c >= '1' && c <= '8') {
        for (int i = column; i < column + (c - '0'); i++) {
          setPieceAt(i, row, null);
        }
        column += (c - '0');
      } else {

        setPieceAt(
            row,
            column,
            new Piece(
                charToPieceType.get(Character.toLowerCase(c)),
                Arrays.asList(row, column),
                c <= 'z' ? Piece.Color.BLACK : Piece.Color.WHITE));

        column++;
      }
    }
  }

  public boolean isValidMove(Piece piece, int column, int row) {
    int vecX = column - piece.getActPosition().get(1);
    int vecY = row - piece.getActPosition().get(0);
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

  public boolean isBlocked(Piece piece, int newColumn, int newRow) {
    List<Integer> oldPos = piece.getActPosition();
    List<Integer> vec = Arrays.asList(newColumn - oldPos.getFirst(), newRow - oldPos.get(1));
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
        i < ((newColumn - oldPos.getFirst()) * vec.getFirst())
            || i < ((newRow - oldPos.get(1)) * vec.get(1));
        i++) {
      if (this.board.get(oldPos.getFirst() + i * vec.getFirst()).get(oldPos.get(1) + i * vec.get(1))
          != null) {
        return true;
      }
    }
    return false;
  }
}
