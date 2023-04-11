package hwr.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.creation.Project;
import project.creation.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class testProject {
    final Project case1 = new Project("Test", LocalDateTime.now(), LocalDate.now());
    final Project case2 = new Project("Test2", LocalDateTime.now(), LocalDate.now().plusDays(2));
    @Test
    void project_canBeCreated(){
        Task t = new Task();
        case1.addTask(t);
        Assertions.assertEquals(case1,case1);
    }

    @Test
    void project_changeDeadline(){
        Task t = new Task();
        case2.addTask(t);
        case2.changeDeadline(LocalDate.now());
        Assertions.assertEquals(case1.getDate(),case2.getDate());
    }
}
