package hwr.oop.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hwr.oop.project.Project;
import hwr.oop.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class testProject {
    final Project case1 = new Project("Test", LocalDateTime.now(), LocalDate.now());
    final Project case2 = new Project("Test2", LocalDateTime.now(), LocalDate.now().plusDays(2));

    @Test
    void project_canBeCreated() {
        Assertions.assertEquals(case1, case1); // benötigt?
    }


    @Test
    void project_setDate() {
        final LocalDate date = LocalDate.now();
        case2.setDate(date);
        Assertions.assertEquals(case2.getDate(), date);
    }

    @Test
    void project_setName() {
        case1.setName("ICE");
        Assertions.assertEquals("ICE", case1.getName());
    }

    @Test
    void project_setTime() {
        final LocalDateTime dateTime = LocalDateTime.now().plusDays(2);
        case1.setTime(dateTime);
        Assertions.assertEquals(dateTime, case1.getTime());
    }
}