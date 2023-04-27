package hwr.oop.group4.todo.persistence;

import hwr.oop.group4.todo.Project;
import hwr.oop.group4.todo.Task;
import hwr.oop.group4.todo.TodoList;
import net.javacrumbs.jsonunit.assertj.JsonAssert;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersistedTodoListTest {

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

        var persistedTodoList = new PersistedTodoList(todoList);

        // System.out.println(persistedTodoList.toString());

        JsonAssertions.assertThatJson(persistedTodoList.toString()).isEqualTo("{\"maybeList\":[],\"projects\":[{\"name\":\"project a\",\"description\":\"\",\"tasks\":[{\"name\":\"task b\",\"description\":\"description b\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"},{\"name\":\"task c\",\"description\":\"description c\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"}],\"tags\":[]}],\"inTray\":[],\"loseTasks\":[{\"name\":\"task a\",\"description\":\"description a\",\"priority\":0,\"tags\":[],\"status\":\"OPEN\"}]}");
    }
}
