package hwr.oop.chess.application.figures;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FigureTest {
    @Test
    void createBlackPawn() {
        PawnFigure pawn = new PawnFigure(FigureColor.BLACK);

        Assertions.assertThat(pawn.color()).isEqualTo(FigureColor.BLACK);
        Assertions.assertThat(pawn.type()).isEqualTo(FigureType.PAWN);
    }

    @Test
    void createWhitePawn() {
        PawnFigure pawn = new PawnFigure(FigureColor.WHITE);

        Assertions.assertThat(pawn.color()).isEqualTo(FigureColor.WHITE);
        Assertions.assertThat(pawn.type()).isEqualTo(FigureType.PAWN);
    }
}
