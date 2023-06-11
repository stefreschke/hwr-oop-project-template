package hwr.oop;

import hwr.oop.persistence.PersistenceAdapter;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ListJsonTest {
    @Test
    void saveDataTest() {
        //Backup contents of data.json
        StringBuilder jsonBackup;
        try (FileReader reader = new FileReader("data.json")) {
            int character;
            jsonBackup = new StringBuilder();
            while ((character = reader.read()) != -1) {
                jsonBackup.append((char) character);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ToDoList assertToDoList = new ToDoList("MyList", "data.json");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", new Bucket("Uni"), Priority.HIGH, LocalDate.of(2024,1,1), EstimatedTime.SHORT);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", new Bucket("Math"), Priority.MEDIUM, LocalDate.of(2024,1,1), EstimatedTime.SHORT);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", new Bucket("Personal"), Priority.LOW, LocalDate.of(2024,1,1), EstimatedTime.SHORT);
        assertToDoList.add(item);
        assertToDoList.add(item2);
        assertToDoList.add(item3);
        new PersistenceAdapter().saveData(assertToDoList);
        try {
            StringBuilder jsonIn;
            try (FileReader reader = new FileReader("data.json")) {
                int character;
                jsonIn = new StringBuilder();
                while ((character = reader.read()) != -1) {
                    jsonIn.append((char) character);
                }
            }
            assertThat(jsonIn).hasToString("{\"name\":\"MyList\",\"items\":[{\"title\":\"Finish Math homework\",\"description\":\"I need to do tasks 5 - 10b.\",\"bucket\":{\"bucketName\":\"Uni\"},\"priority\":\"HIGH\",\"createdAt\":\"2023-06-11\",\"state\":\"TODO\",\"dueDate\":\"2024-01-01\"},{\"title\":\"Calculate Something\",\"description\":\"More Math over here\",\"bucket\":{\"bucketName\":\"Math\"},\"priority\":\"MEDIUM\",\"createdAt\":\"2023-06-11\",\"state\":\"TODO\",\"dueDate\":\"2024-01-01\"},{\"title\":\"Be Amazing\",\"description\":\"Just Do It\",\"bucket\":{\"bucketName\":\"Personal\"},\"priority\":\"LOW\",\"createdAt\":\"2023-06-11\",\"state\":\"TODO\",\"dueDate\":\"2024-01-01\"}],\"fileName\":\"data.json\",\"buckets\":[]}");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Sorry...File could not be neither found nor created.");
        } catch (NullPointerException e) {
            System.out.println("File is empty");
        }
        //Restore contents of data.json
        try (FileWriter fr = new FileWriter("data.json")){
            fr.write(jsonBackup.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void loadDataTest() {
        PersistenceAdapter pa = new PersistenceAdapter();
        ToDoList assertToDoList = pa.loadData();
        assertThat(assertToDoList.getName()).isEqualTo("MyList");
    }
}

