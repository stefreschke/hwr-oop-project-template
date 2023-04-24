package hwr.oop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ListClassTest {

    @ParameterizedTest
    @ValueSource(strings = {"List", "New List"})
    public void setListName(String Name) {
        List list = new List();
        list.setName(Name);
        String TestName = list.getName();
        assertThat(TestName).isEqualTo(Name);
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
