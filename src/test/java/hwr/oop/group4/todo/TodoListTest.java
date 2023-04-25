package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoListTest {

    @Test
    void emptyOnConstructionGetMaybeList() {
        final TodoList todo = new TodoList();

        assertThat(todo.getMaybeList().size()).isEqualTo(0);
    }

    @Test
    void emptyOnConstructionGetProjects() {
        final TodoList todo = new TodoList();

        assertThat(todo.getProjects().size()).isEqualTo(0);
    }

    @Test
    void emptyOnConstructionGetInTray() {
        final TodoList todo = new TodoList();

        assertThat(todo.getInTray().size()).isEqualTo(0);
    }

    @Test
    void emptyOnConstructionGetLoseTasks() {
        final TodoList todo = new TodoList();

        assertThat(todo.getLoseTasks().size()).isEqualTo(0);
    }

    @Test
    void inTray() {
        final TodoList todo = new TodoList();

        todo.addIdea(new Idea("new Idea"));
        todo.addIdea(new Idea("new Idea"));

        assertThat(todo.getInTray().size()).isEqualTo(1);
        assertThat(todo.getInTray()).contains(new Idea("new Idea"));
    }

    @Test
    void loseTasks() {
        final TodoList todo = new TodoList();
        final Task taskA = new Task.TaskBuilder().name("task").build();

        todo.addLoseTask(taskA);
        todo.addLoseTask(taskA);

        assertThat(todo.getLoseTasks().size()).isEqualTo(1);
        assertThat(todo.getLoseTasks()).contains(taskA);
    }

    @Test
    void projects() {
        final TodoList todo = new TodoList();

        final Project projectA = new Project.ProjectBuilder().name("name").description("desc").build();

        todo.addProject(projectA);
        todo.addProject(projectA);

        assertThat(todo.getProjects().size()).isEqualTo(2);
        assertThat(todo.getProjects().get(0)).isEqualTo(projectA);
        assertThat(todo.getProjects().get(1)).isEqualTo(projectA);
    }

    @Test
    void someDayMaybe() {
        final TodoList todo = new TodoList();
        final Project projectA = new Project.ProjectBuilder().name("name").description("desc").build();

        todo.addSomedayMaybeProject(projectA);
        todo.addSomedayMaybeProject(projectA);

        assertThat(todo.getMaybeList().size()).isEqualTo(2);
        assertThat(todo.getMaybeList()).contains(projectA);
    }

}
