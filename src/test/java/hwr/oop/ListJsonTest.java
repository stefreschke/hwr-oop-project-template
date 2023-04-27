package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ListJsonTest {
    @Test
    public void listToJsonTest() {
        List assertList = new List("myList", "listTest");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", false, Priority.HIGH, new Project(""));
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", false, Priority.MEDIUM, new Project(""));
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", false, Priority.LOW, new Project(""));
        assertList.add(item);
        assertList.add(item2);
        assertList.add(item3);
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
            String testDate = item.getCreatedAt();
            assertThat(jsonIn.toString()).isEqualTo("{\"Name\":\"myList\",\"ListToDos\":[{\"id\":0,\"title\":\"Finish Math homework\",\"description\":\"I need to do tasks 5 - 10b.\\nCreated " + testDate + "\",\"tag\":\"Uni\",\"done\":false,\"priority\":\"HIGH\",\"project\":{\"title\":\"\"}},{\"id\":1,\"title\":\"Calculate Something\",\"description\":\"More Math over here\\nCreated " + testDate + "\",\"tag\":\"Math\",\"done\":false,\"priority\":\"MEDIUM\",\"project\":{\"title\":\"\"}},{\"id\":2,\"title\":\"Be Amazing\",\"description\":\"Just Do It\\nCreated " + testDate + "\",\"tag\":\"Personal\",\"done\":false,\"priority\":\"LOW\",\"project\":{\"title\":\"\"}}],\"fileName\":\"listTest\"}");
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (NullPointerException e) {
            System.out.println("File is empty");
        }
    }
    @Test
    public void jsonToListTest() {
        Program program = new Program();
        List assertList = program.loadList("listTest.ipynb");
        assertThat(assertList.getName()).isEqualTo("myList");
    }
}

