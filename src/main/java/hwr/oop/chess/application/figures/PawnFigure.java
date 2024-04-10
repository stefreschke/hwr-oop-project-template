package hwr.oop.chess.application.figures;

public class PawnFigure implements Figure {
    private final FigureColor color;
    public PawnFigure(FigureColor color) {
        this.color = color;
    }

    public FigureColor color() {
        return color;
    }
    public FigureType type () {
        return FigureType.PAWN;
    }
}
