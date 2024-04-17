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
        Position oldPosition = getPosition();
        boolean isFieldBlocked = Board.isFigureOnField(newPosition);
        if (getColor() == FigureColor.BLACK) {
            // Move one field straight down
            if(!isFieldBlocked
                    && (oldPosition.x() == newPosition.x())
                    && (oldPosition.y() - 1) == (newPosition.y())){
                return true;
            }

            // Move one field diagonally
            if (isFieldBlocked // check if a different figure is on the new field
                    && Board.getFigureOnField(newPosition).getColor() == FigureColor.WHITE
                    && (Math.abs(oldPosition.x() - newPosition.x()) == 1)
                    && (oldPosition.y() - 1) == newPosition.y()) {
                return true;
            }

            // Moving two fields from the start position
            return isFieldBlocked
                    && (oldPosition.y() == 7)
                    && (oldPosition.x() - 2) == newPosition.x();

        } else {
            public boolean canMoveTo(Position newPosition) {
                Position oldPosition = getPosition();
                if (getColor)
            }
            // Move one field straight up
            return !isFieldBlocked
                    && (oldPosition.x() == newPosition.x())
                    && (oldPosition.y() + 1) == newPosition.y();
        }
    }

}
