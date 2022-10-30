package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// TODO Delete this placeholder test.
class JavaExampleTest {
    @Test
    void get_IsTrue() {
        KotlinExample example = new KotlinExample();
        boolean result = example.get();
        Assertions.assertThat(result).isTrue();
    }
}
