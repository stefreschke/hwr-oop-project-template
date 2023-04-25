package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListClassTest {

    @Test
    public void setListName() {
        List list = new List("wrongName");
        list.setName("rightName");
        String TestName = list.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    public void addTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem(0,"Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH);
        list.add(item);
        ToDoItem[] itemList = new ToDoItem[1];
        itemList[0] = item;
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

    @Test
    public void removeTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem(0,"Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH);
        list.add(item);
        list.remove(0);
        ToDoItem[] itemList = new ToDoItem[0];
        assertThat(list.getListToDos()).isEqualTo(itemList);
    }

/*
    @ParameterizedTest
    @ValueSource(strings = {"List", "New List"})
    public void createToDodescriptionTest(String description) {
        ToDoItem ToDo = new ToDoItem();
        ToDo.setDescription(description);
        String ToDodescription = ToDo.getdescription();
        assertThat(ToDodescription).isEqualTo(description);
    }

    @ParameterizedTest
    @ValueSource(strings = {"List", "New List"})
    public void createToDotitleTest(String title) {
        ToDoItem ToDo = new ToDoItem();
        ToDo.setTitle(title);
        String ToDotile = ToDo.gettitle();
        assertThat(ToDotile).isEqualTo(title);
    }

    @ParameterizedTest
    @ValueSource(strings = {"List", "New List"})
    public void createToDodoneTest(boolean done) {
        ToDoItem ToDo = new ToDoItem();
        ToDo.setDone(done);
        String ToDodone = ToDo.getdone();
        assertThat(ToDodone).isEqualTo(done);
    }

    @ParameterizedTest
    @ValueSource(strings = {"List", "New List"})
    public void createToDodescriptionTest(Priority priority) {
        ToDoItem ToDo = new ToDoItem();
        ToDo.setPriority(priority);
        String ToDopriority = ToDo.getpriority();
        assertThat(ToDopriority).isEqualTo(priority);
    }
*/
}
