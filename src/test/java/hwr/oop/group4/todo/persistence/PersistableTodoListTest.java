package hwr.oop.group4.todo.persistence;

import hwr.oop.group4.todo.core.Project;
import hwr.oop.group4.todo.core.Task;
import hwr.oop.group4.todo.core.TodoList;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class PersistableTodoListTest {

    @Test
    void toJsonObject() {
        var todoList = new TodoList();

        var projectA = new Project.ProjectBuilder()
                .name("project a")
                .build();

        var taskA = new Task.TaskBuilder()
                .name("task a")
                .description("description a")
                .build();
        var taskB = new Task.TaskBuilder()
                .name("task b")
                .description("description b")
                .build();
        var taskC = new Task.TaskBuilder()
                .name("task c")
                .description("description c")
                .build();

        projectA.addTask(taskB);
        projectA.addTask(taskC);

        todoList.addProject(projectA);
        todoList.addLoseTask(taskA);

        var persistedTodoList = new PersistableTodoList(todoList);

        assertThatJson(persistedTodoList.toString())
                .when(Option.IGNORING_ARRAY_ORDER)
                .isEqualTo("{\"projects\":[{\"name\":\"project a\",\"description\":\"\",\"tasks\":[{\"name\":\"task c\",\"description\":\"description c\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"},{\"name\":\"task b\",\"description\":\"description b\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"}],\"tags\":[]}],\"maybeList\":[],\"inTray\":[],\"loseTasks\":[{\"name\":\"task a\",\"description\":\"description a\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"}]}");
    }
}
