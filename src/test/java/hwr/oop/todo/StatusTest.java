package hwr.oop.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void getCondition() {
        Status status = new Status("New");
        Assertions.assertThat("New").isEqualTo(status.getCondition());
    }

    @Test
    void setCondition() {
        Status status = new Status("");
        status.setCondition("New");
        Assertions.assertThat("New").isEqualTo(status.getCondition());
    }
}