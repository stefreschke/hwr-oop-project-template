package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MainTest {
    @Test
    public void mainTest() {
        try {
            Main.main(new String[] {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
