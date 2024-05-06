package hwr.oop.doppelkopf.group6;

import java.io.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileTest {
  private final PrintStream standardErr = System.err;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final String fileName = "/Users/lukaskarsten/Desktop/test.txt";

  @BeforeEach
  void setUp() {
    System.setErr(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  void tearDown() {
    System.setErr(standardErr);
  }

  @Test
  void testFileExistence() {
    String nonExistingFileName = "/Users/lukaskarsten/Desktop/nonExistingFile.txt";
    File file = new File(fileName);
    File nonExistingfFile = new File(nonExistingFileName);
    SoftAssertions.assertSoftly(
        softly -> {
          softly.assertThat(file.exists()).isTrue();
          softly.assertThat(nonExistingfFile.exists()).isFalse();
        });
  }
}
