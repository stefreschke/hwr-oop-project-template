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
        TodoList todoList = new TodoList();

        Project projectA = new Project.ProjectBuilder()
                .name("project a")
                .build();

        Task taskA = new Task.TaskBuilder()
                .name("task a")
                .description("description a")
                .build();
        Task taskB = new Task.TaskBuilder()
                .name("task b")
                .description("description b")
                .build();
        Task taskC = new Task.TaskBuilder()
                .name("task c")
                .description("description c")
                .build();

        projectA.addTask(taskB);
        projectA.addTask(taskC);

        todoList.addProject(projectA);
        todoList.addLoseTask(taskA);

        Persistable persistedTodoList = new PersistableTodoList(todoList);

        assertThatJson(persistedTodoList.exportAsString())
                .when(Option.IGNORING_ARRAY_ORDER)
                .isEqualTo("{\"projects\":[{\"name\":\"project a\",\"description\":\"\",\"tasks\":[{\"name\":\"task c\",\"description\":\"description c\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"},{\"name\":\"task b\",\"description\":\"description b\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"}],\"tags\":[]}],\"maybeList\":[],\"inTray\":[],\"loseTasks\":[{\"name\":\"task a\",\"description\":\"description a\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"}]}");
    }
}
