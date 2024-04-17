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

    public boolean canMoveTo(Position newPosition) {
        Position oldPosition = this.currentPosition;
        boolean isFieldBlocked = Board.isFigureOnField(newPosition);
        Figure otherFigure = Board.getFigureOnField(newPosition);

        if (this.color == FigureColor.BLACK) {
            // Move one field straight down
            if(!isFieldBlocked
                    && (oldPosition.x() == newPosition.x())
                    && (oldPosition.y() - 1) == (newPosition.y())){
                return true;
            }

            // Move one field diagonally
            if (isFieldBlocked // check if a different figure is on the new field
                    && otherFigure.getColor() == FigureColor.WHITE
                    && (Math.abs(oldPosition.x() - newPosition.x()) == 1)
                    && (oldPosition.y() - 1) == newPosition.y()) {
                return true;
            }

            // Moving two fields down from the start position
            return isFieldBlocked // to-do: missing the checking of the first field
                    && (oldPosition.y() == 7)
                    && (oldPosition.y() - 2) == newPosition.y()
                    && (oldPosition.x() == newPosition.x());

        } else {

            // Move one field straight up
            return !isFieldBlocked
                    && (oldPosition.x() == newPosition.x())
                    && (oldPosition.y() + 1) == newPosition.y();

            // Move two fields up from the start position
            return isFieldBlocked // to-do: missing the checking of the first field
                    && (oldPosition.y() == 2)
                    && (oldPosition.y() + 2) == newPosition.y()
                    && (oldPosition.x() == newPosition.x());
        }
    }

    public void moveTo(Position newPosition) {}

    public boolean isOnField(Position field){
        return true;
    }
    public boolean isCaptured(){
        return true;
    }
    public void setPosition(Position position){}
    public FigureColor getColor(){
        return this.color;
    }
}
