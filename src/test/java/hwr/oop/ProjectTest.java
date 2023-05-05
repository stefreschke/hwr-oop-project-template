package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {
    @Test
    void setTitle() {
        Project project = new Project("");
        project.setTitle("testtitle");
        String Testname = project.getTitle();
        assertThat(Testname).isEqualTo("testtitle");
    }

    @Test
    void getTitle() {
        Project project = new Project("testtitle");
        String Testname = project.getTitle();
        assertThat(Testname).isEqualTo("testtitle");
    }
}
