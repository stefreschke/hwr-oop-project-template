package hwr.oop.handlerTests;

import hwr.oop.Bucket;
import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.handler.BucketHandler;
import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class BucketHandlerTest {
    @Test
    void bucketHandlerTest(){
        BucketHandler bucketHandler = new BucketHandler();
        assertThat(bucketHandler).isNotNull();
    }
    @Test
     void testRenameBucket() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        BucketHandler.handleUserCommand(toDoList, new ConsoleUserInterface(System.out, System.in), new String[]{"gtd", "rnb", "0", "test"});
        assertThat(Objects.requireNonNull(Util.getElementAtIndex(toDoList.getBuckets(), 0)).getBucketName()).isEqualTo("test");
    }
    @Test
    void testRenameBucketIndexOutOfBounds() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        toDoList.addBucket(new Bucket("Bucket1"));
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("n\n".getBytes());
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), in);
        BucketHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "rnb", "1", "test"});
        String expected = "\u001B[1;31mThere is nothing at that index... \uD83E\uDD78\u001B[0m\n" +
                "Try again? (y/n)\n" +
                "Okay, I'll leave you alone then. \uD83D\uDC4B\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
     void testShowBuckets() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        toDoList.addBucket(new Bucket("Bucket1"));
        toDoList.addBucket(new Bucket("Bucket2"));
        BucketHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "sb"});
        String expected1 = "\uD83E\uDEA3Bucket1\n";
        String expected2 = "\uD83E\uDEA3Bucket2\n";
        String actual = outBuffer.toString();
        assertThat(actual).contains(expected1).contains(expected2);
    }
    @Test
    void testShowBucketsEmpty() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        BucketHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "sb"});
        String expected = "\uD83D\uDC40Looks Empty here... Add some buckets!\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testToManyArguments(){
        ToDoList toDoList = new ToDoList("MyToDoList");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        BucketHandler.handleUserCommand(toDoList, cui, new String[]{"gtd"});
        String expected = "\u001B[1;31mInvalid number of arguments\u001B[0m\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testUnknownCommand(){
        ToDoList toDoList = new ToDoList("MyToDoList");
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        BucketHandler.handleUserCommand(toDoList, cui, new String[]{"gtd", "unknown"});
        String expected = "\u001B[1;31mUnknown command\u001B[0m\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testArgsNull(){
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(outBuffer), System.in);
        BucketHandler.handleUserCommand(null, cui, new String[]{"gtd", "sb"});
        String expected = "[1;33mType gtd help to get help on commands.[0m\n";
        String actual = outBuffer.toString();
        assertThat(actual).isEqualTo(expected);
    }
}
