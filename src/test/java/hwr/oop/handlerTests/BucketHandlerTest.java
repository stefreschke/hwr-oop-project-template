package hwr.oop.handlerTests;

import hwr.oop.Bucket;
import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.handler.BucketHandler;
import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class BucketHandlerTest {
    @Test
     void testCreateBucket() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        BucketHandler.handleUserCommand(toDoList, null, new String[]{"gtd", "cb", "test"});
        assertThat(toDoList.getBuckets()).hasSize(1);
    }
    @Test
     void testRenameBucket() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        BucketHandler.handleUserCommand(toDoList, new ConsoleUserInterface(System.out, System.in), new String[]{"gtd", "rnb", "0", "test"});
        assertThat(Objects.requireNonNull(Util.getElementAtIndex(toDoList.getBuckets(), 0)).getBucketName()).isEqualTo("test");
    }
    @Test
     void testShowBuckets() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        toDoList.addBucket(new Bucket("Bucket1"));
        toDoList.addBucket(new Bucket("Bucket2"));
        BucketHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "sb"});
        String expected = "\uD83E\uDEA3Bucket1\n" +
                "\uD83E\uDEA3Bucket2\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
}
