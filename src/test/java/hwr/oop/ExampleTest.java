package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ExampleTest {

  @Test
  void get_IsTrue() {
    Example example = new Example();
    String result = example.get();
    Assertions.assertThat(result).startsWith("Hello").endsWith("World!");
  }
}
