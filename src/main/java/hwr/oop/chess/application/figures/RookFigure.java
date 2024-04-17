package hwr.oop.chess.application.figures;

import hwr.oop.chess.application.Board;
import hwr.oop.chess.application.Position;

public class RookFigure implements Figure {
    Position startPosition = null;
    Position currentPosition = null;
    FigureType type = null;
    FigureColor color = null;

    public RookFigure(FigureColor color, Position position) {
        this.type = FigureType.ROOK;
        this.color = color;
        this.startPosition = position;
        this.currentPosition = position;
    }

    @Override
    public boolean canMoveTo(Position newPosition) {
        // #Todo Write Castling
        Position oldPosition = this.currentPosition;
        Figure otherFigure = Board.getFigureOnField(newPosition);
        boolean isOtherFigureOnRoad = this.checkOtherFigureOnRoad(oldPosition, newPosition);

        if(!isOtherFigureOnRoad){
            if(otherFigure == null
                    && oldPosition.x() == newPosition.x()
                    && oldPosition.y() == newPosition.y()){
                return true;
            } else{
                return otherFigure.getColor() == this.getColor();
            }
        } else {
            return false;
        }
    }

    @Override
    public void moveTo(Position newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public boolean isOnField(Position field){
        return this.currentPosition != null && this.currentPosition.equals(field);
    }

    @Override
    public boolean isCaptured(){
        return this.currentPosition != null;
    }

    @Override
    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    @Override
    public Position getPosition() {
        return this.currentPosition;
    }

    @Override
    public FigureColor getColor(){
        return this.color;
    }

    @Override
    public FigureType getType() {
        return this.type;
    }

    private boolean checkOtherFigureOnRoad(Position currentPosition, Position newPosition) {
        int x = currentPosition.x();
        int y = currentPosition.y();

        if (x < newPosition.x()){
            x++;
            for (; x < newPosition.x(); x++){
                if(Board.isFigureOnField(new Position(x, y))){
                    return true;
                }
            }
        } else {
            x--;
            for (; x > newPosition.x(); x--){
                if(Board.isFigureOnField(new Position(x, y))){
                    return true;
                }
            }
        }

     if (y < newPosition.y()){
            y++;
            for (; y < newPosition.y(); y++){
                if(Board.isFigureOnField(new Position(x, y))){
                    return true;
                }
            }
        } else {
            y--;
            for (; y > newPosition.y(); y--){
                if(Board.isFigureOnField(new Position(x, y))){
                    return true;
                }
            }
        }

        return false;
    }
}
