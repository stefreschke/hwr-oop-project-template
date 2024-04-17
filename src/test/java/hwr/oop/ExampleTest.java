package hwr.oop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;


class ExampleTest {

  @Test
  void get_IsTrue() {
    Example example = new Example();
    String result = example.get();
    assertThat(result).startsWith("Hello").endsWith("World!");
  }

  @Test
  void main_NoException() {
    assertDoesNotThrow(() -> Example.main(new String[]{}));
  }
}
