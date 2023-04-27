package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TodoListTest {

    @Test
    void emptyOnConstructionGetMaybeList() {
        final TodoList todo = new TodoList();

        assertThat(todo.getMaybeList()).isEmpty();
    }

    @Test
    void emptyOnConstructionGetProjects() {
        final TodoList todo = new TodoList();

        assertThat(todo.getProjects()).isEmpty();
    }

    @Test
    void emptyOnConstructionGetInTray() {
        final TodoList todo = new TodoList();

        assertThat(todo.getInTray()).isEmpty();
    }

    @Test
    void emptyOnConstructionGetLoseTasks() {
        final TodoList todo = new TodoList();

        assertThat(todo.getLoseTasks()).isEmpty();
    }

    @Test
    void inTray() {
        final TodoList todo = new TodoList();

        todo.addIdea(new Idea("new Idea"));
        todo.addIdea(new Idea("new Idea"));

        assertThat(todo.getInTray()).hasSize(1);
        assertThat(todo.getInTray()).contains(new Idea("new Idea"));
    }

    @Test
    void loseTasks() {
        final TodoList todo = new TodoList();
        final Task taskA = new Task.TaskBuilder().name("task").build();

        todo.addLoseTask(taskA);
        todo.addLoseTask(taskA);

        assertThat(todo.getLoseTasks()).hasSize(1);
        assertThat(todo.getLoseTasks()).contains(taskA);
    }

    @Test
    void projects() {
        final TodoList todo = new TodoList();

        final Project projectA = new Project.ProjectBuilder().name("name").description("desc").build();

        todo.addProject(projectA);
        todo.addProject(projectA);

        assertThat(todo.getProjects()).hasSize(2);
        assertThat(todo.getProjects().get(0)).isEqualTo(projectA);
        assertThat(todo.getProjects().get(1)).isEqualTo(projectA);
    }

    @Test
    void someDayMaybe() {
        final TodoList todo = new TodoList();
        final Project projectA = new Project.ProjectBuilder().name("name").description("desc").build();

        todo.addSomedayMaybeProject(projectA);
        todo.addSomedayMaybeProject(projectA);

        assertThat(todo.getMaybeList()).hasSize(2);
        assertThat(todo.getMaybeList()).contains(projectA);
    }

    @Test
    void removeProject() {
        final TodoList todo = new TodoList();
        final Project project = new Project.ProjectBuilder().name("name").description("desc").build();

        todo.addProject(project);
        todo.removeProject(project);

        assertThat(todo.getProjects()).isEmpty();
    }

    @Test
    void removeProjectSomeDayMaybe() {
        final TodoList todo = new TodoList();
        final Project project = new Project.ProjectBuilder().name("name").description("desc").build();

        todo.addSomedayMaybeProject(project);
        todo.removeSomedayMaybeProject(project);

        assertThat(todo.getMaybeList()).isEmpty();
    }

    @Test
    void removeTask() {
        final TodoList todo = new TodoList();
        final Task task = new Task.TaskBuilder().name("name").description("desc").build();

        todo.addLoseTask(task);
        todo.removeLoseTask(task);

        assertThat(todo.getLoseTasks()).isEmpty();
    }

    @Test
    void removeIdea() {
        final TodoList todo = new TodoList();
        final Idea idea = new Idea("idea name");

        todo.addIdea(idea);
        todo.removeIdea(idea);

        assertThat(todo.getInTray()).isEmpty();
    }
}
