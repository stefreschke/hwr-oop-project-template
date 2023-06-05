package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewBoxTest {
    @Test
    void canCreateFirstBox(){
        BoxInterface firstBox = new FirstBox(1);
        BoxInterface normalBox = new NormalBox(3, firstBox);
        BoxInterface lastBox = new LastBox(7, normalBox);
        Assertions.assertThat(firstBox.getNextBox()).isNotNull();
    }
}
