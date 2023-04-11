package hwr.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.creation.Project;
import project.creation.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CanCreateProject {
    @Test
    void project_canBeCreated(){
        final Project case1 = new Project("Test", LocalDateTime.now(), LocalDate.now());
        Task t = new Task();
        case1.addTask(t);
        Assertions.assertEquals(case1,case1);
    }
}
