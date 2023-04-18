package hwr.oop;

import hwr.oop.dataclasses.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

// TODO Delete this placeholder test.
class DataclassesTest {
    @Test
    void get_IsTrue() {
        Task example = new Task(1,"test","test",TaskState.IN_PROGRESS,null,null, LocalDate.now(),LocalDate.now());
        Integer result = example.getId();
        Assertions.assertThat(result).isEqualTo(1);
    }
}