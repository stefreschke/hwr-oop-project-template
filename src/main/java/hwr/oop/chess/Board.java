package hwr.oop.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Board {
  private List<List<Piece>> playBoard;
  private Map<Character, Piece.PieceType> charToPieceType;
  private Logger logger = Logger.getLogger(getClass().getName());

  public Board() {
    charToPieceType();
    playBoard = new ArrayList<>(8);

    for (int i = 0; i < 8; i++) {
      playBoard.add(new ArrayList<>(8));
    }

    for (List<Piece> row : playBoard) {
      for (int i = 0; i < 8; i++) {
        row.add(null);
      }
    }
  }

  public Piece getPieceAt(int column, int row) {
    return this.playBoard.get(column).get(row);
  }

  public List<List<Piece>> getPlayBoard() {
    return playBoard;
  }

  private void setPieceAt(int column, int row, Piece piece) {
    this.playBoard.get(row).set(column, piece);
  }

  public void setBoardToFen(String fen) {
    int column = 0;
    int row = 7;

    for (char c : fen.toCharArray()) {

      if (c == '/') {
        row--;
        column = 0;
      } else if (c >= '1' && c <= '8') {
        for (int i = column; i < column + (c - '0'); i++) {
          setPieceAt(row, i, null);
        }
        column += (c - '0');
      } else {

        setPieceAt(
                column, row,
                new Piece(
                charToPieceType.get(Character.toLowerCase(c)),
                Arrays.asList(row, column),
                Character.isUpperCase(c) ? Piece.Color.WHITE : Piece.Color.BLACK));

        column++;
      }
    }
  }

  public void initBoard() {
    setBoardToFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
  }

  public void charToPieceType() {
    charToPieceType = new HashMap<>();
    charToPieceType.put('r', Piece.PieceType.TURM);
    charToPieceType.put('n', Piece.PieceType.SPRINGER);
    charToPieceType.put('b', Piece.PieceType.LAEUFER);
    charToPieceType.put('q', Piece.PieceType.DAME);
    charToPieceType.put('k', Piece.PieceType.KOENIG);
    charToPieceType.put('p', Piece.PieceType.BAUER);
  }

  public void changePos(int oldCol, int oldRow, int newCol, int newRow) {
    this.playBoard.get(newCol).set(newRow, playBoard.get(oldCol).get(oldRow));
    this.playBoard.get(oldCol).set(oldRow, null);
  }

  public void printBoard() {
    logger.info("Printing Board here");
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
      if (this.playBoard.get(oldPos.getFirst() + i * vec.getFirst()).get(oldPos.get(1) + i * vec.get(1))
          != null) {
        return true;
      }
    }
    return false;
  }
}
