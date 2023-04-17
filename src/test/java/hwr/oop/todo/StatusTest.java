package hwr.oop.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void testCondition() {
        Status status = new Status("New");
        Assertions.assertThat("New").isEqualTo(status.getCondition());
        status.setCondition("New2");
        Assertions.assertThat("New2").isEqualTo(status.getCondition());
    }
}