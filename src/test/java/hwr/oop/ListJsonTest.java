package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.FileReader;
import java.io.IOException;

public class ListJsonTest {
    @Test
    public void listToJsonTest() {
        List assertList = new List();
        assertList.setName("TestName");
        assertList.writeToJSON("listTest.mp3");
        try {
            StringBuilder jsonIn;
            try (FileReader reader = new FileReader("listTest.json")) {
                int character;
                jsonIn = new StringBuilder();
                while ((character = reader.read()) != -1) {
                    jsonIn.append((char) character);
                }
            }
            assertThat(jsonIn.toString()).isEqualTo("{\"Name\":\"TestName\"}");
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (NullPointerException e) {
            System.out.println("File is empty");
        }
    }
    @Test
    public void jsonToListTest() {
        Program program = new Program();
        List assertList = program.loadList("listTest.json");
        assertThat(assertList.getName()).isEqualTo("TestName");
    }
}

