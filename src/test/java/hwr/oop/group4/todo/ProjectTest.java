package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest {

    @Test
    void createProject() {
        var beginAt = LocalDateTime.now();
        var endAt = LocalDateTime.now();
        Project project = new Project("myProject", "myDesc", new HashSet<>(), new HashSet<>(), beginAt, endAt);

        assertThat(project.getName()).isEqualTo("myProject");
        assertThat(project.getDescription()).isEqualTo("myDesc");
        assertThat(project.getTags().size()).isEqualTo(0);
        assertThat(project.getTasks().size()).isEqualTo(0);
        assertThat(project.getBegin()).isEqualTo(beginAt);
        assertThat(project.getEnd()).isEqualTo(endAt);
    }

    @Test
    void setters() {
        var beginAt = LocalDateTime.now();
        var endAt = LocalDateTime.now();
        Project project = new Project("myProject", "myDesc", new HashSet<>(), new HashSet<>(), beginAt, endAt);

        project.setName("myP");
        assertThat(project.getName()).isEqualTo("myP");

        project.setDescription("myD");
        assertThat(project.getDescription()).isEqualTo("myD");

        var myTag = new Tag("myTag");
        project.addTag(myTag);
        assertThat(project.getTags().size()).isEqualTo(1);
        assertTrue(project.getTags().contains(myTag));

        var createdAt = LocalDateTime.now();
        Task myTask = new Task("aName", "aDesc", 3, createdAt, new HashSet<>());
        project.addTask(myTask);
        assertThat(project.getTasks().size()).isEqualTo(1);
        assertTrue(project.getTasks().contains(myTask));

        var newBeginAt = beginAt.plusDays(4);
        var newEndAt = beginAt.plusDays(6);
        project.setBegin(newBeginAt);
        project.setEnd(newEndAt);
        assertThat(project.getBegin()).isEqualTo(newBeginAt);
        assertThat(project.getEnd()).isEqualTo(newEndAt);
    }
}
