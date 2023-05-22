package hwr.oop;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ListJsonTest {
    @Test
    public void listToJsonTest() {
        ToDoList assertToDoList = new ToDoList("myToDoList", "toDoListTest");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        assertToDoList.add(item);
        assertToDoList.add(item2);
        assertToDoList.add(item3);
        assertToDoList.writeToJSON(new ConsoleUserInterface(null, null), "listTest.mp3");
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
            assertThat(jsonIn.toString()).isEqualTo("{\"Name\":\"myToDoList\",\"ToDoListToDos\":[{\"id\":0,\"title\":\"Finish Math homework\",\"description\":\"I need to do tasks 5 - 10b.\\nCreated " + testDate + "\",\"Bucket\":\"Uni\",\"done\":false,\"priority\":\"HIGH\",\"project\":{\"title\":\"\"}},{\"id\":1,\"title\":\"Calculate Something\",\"description\":\"More Math over here\\nCreated " + testDate + "\",\"Bucket\":\"Math\",\"done\":false,\"priority\":\"MEDIUM\",\"project\":{\"title\":\"\"}},{\"id\":2,\"title\":\"Be Amazing\",\"description\":\"Just Do It\\nCreated " + testDate + "\",\"Bucket\":\"Personal\",\"done\":false,\"priority\":\"LOW\",\"project\":{\"title\":\"\"}}],\"fileName\":\"toDoListTest\"}");
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (NullPointerException e) {
            System.out.println("File is empty");
        }
    }
    @Test
    public void jsonToToDoListTest() {
        Program program = new Program();
        ToDoList assertToDoList = program.loadToDoList("listTest.ipynb");
        assertThat(assertToDoList.getName()).isEqualTo("myToDoList");
    }
}

