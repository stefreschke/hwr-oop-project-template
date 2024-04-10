package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KartenStarpelTest {

    @Test
    public void testKartenStarpelInizialiesirung() {
        KartenStarpel kartenStarpel = new KartenStarpel();
        List<Karte> Karten = kartenStarpel.neuerKartenStarpel();
        Assertions.assertThat(Karten.size()).isEqualTo(Farbe.getFarben().size()*Wert.getZahlen().size());
    }

}
