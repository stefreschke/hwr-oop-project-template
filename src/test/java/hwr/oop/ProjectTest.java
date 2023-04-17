package hwr.oop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {
    @ParameterizedTest
    @ValueSource(strings = {"Title", "New Title"})
    public void projectTitleSetterTest(String title) {
        Project project = new Project();
        project.setTitle(title);
        String testTitle = project.title;
        assertThat(testTitle).isEqualTo(title);
    }
}
