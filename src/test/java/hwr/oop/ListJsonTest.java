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
        try {
            assertToDoList.writeToJSON(new ConsoleUserInterface(null, null), "listTest.mp3");
        } catch (ToDoList.FileNotFoundAndCoundNotCreateException e) {
            throw new RuntimeException(e);
        }
        try {
            StringBuilder jsonIn;
            try (FileReader reader = new FileReader("listTest.json")) {
                int character;
                jsonIn = new StringBuilder();
                while ((character = reader.read()) != -1) {
                    jsonIn.append((char) character);
                }
            }
            assertThat(jsonIn.toString()).isEqualTo("{\"name\":\"myToDoList\",\"items\":[{\"title\":\"Finish Math homework\",\"description\":\"I need to do tasks 5 - 10b.\",\"bucket\":{\"bucketName\":\"Uni\"},\"priority\":\"HIGH\",\"createdAt\":\""  + item.getCreatedAt() + "\",\"state\":\"TODO\"},{\"title\":\"Calculate Something\",\"description\":\"More Math over here\",\"bucket\":{\"bucketName\":\"Math\"},\"priority\":\"MEDIUM\",\"createdAt\":\""  + item2.getCreatedAt() + "\",\"state\":\"TODO\"},{\"title\":\"Be Amazing\",\"description\":\"Just Do It\",\"bucket\":{\"bucketName\":\"Personal\"},\"priority\":\"LOW\",\"createdAt\":\""  + item3.getCreatedAt() + "\",\"state\":\"TODO\"}],\"fileName\":\"toDoListTest\",\"buckets\":[]}");
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

