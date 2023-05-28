package hwr.oop.handlerTests;

import hwr.oop.ToDoList;
import hwr.oop.handler.BucketHandler;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketHandlerTest {
    @Test
    public void testCreateBucket() {
        ToDoList toDoList = new ToDoList("MyToDoList");
        BucketHandler.handleUserCommand(toDoList, null, new String[]{"gtd", "cb", "test"});
        assertThat(toDoList.getBuckets()).hasSize(1);
    }
}
