package hwr.oop.chess.persistence;

public class FenNotation {
    private String position;
    private String activeColor;
    private String castling;
    private String enPassant;
    private int halfmoveClock;
    private int fullmoveNumber;

    public FenNotation(String position, String activeColor, String castling, String enPassant,
               int halfmoveClock, int fullmoveNumber) {
        this.position = position;
        this.activeColor = activeColor;
        this.castling = castling;
        this.enPassant = enPassant;
        this.halfmoveClock = halfmoveClock;
        this.fullmoveNumber = fullmoveNumber;
    }

    // getter and setter
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getActiveColor() {
        return this.activeColor;
    }
    public void setActiveColor(String activeColor) {
        this.activeColor = activeColor;
    }
    public String getCastling() {
        return this.castling;
    }
    public void setCastling(String castling) {
        this.castling = castling;
    }
    public String getEnPassant() {
        return this.enPassant;
    }
    public void setEnPassant(String enPassant) {
        this.enPassant = enPassant;
    }
    public int getHalfmoveClock() {
        return this.halfmoveClock;
    }
    public void setHalfmoveClock(int halfmoveClock) {
        this.halfmoveClock = halfmoveClock;
    }
    public int getFullmoveNumber() {
        return this.fullmoveNumber;
    }
    public void setFullmoveNumber(int fullmoveNumber) {
        this.fullmoveNumber = fullmoveNumber;
    }
}
