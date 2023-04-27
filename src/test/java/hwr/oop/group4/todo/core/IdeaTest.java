package hwr.oop.group4.todo.core;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;
import hwr.oop.group4.todo.core.Idea;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IdeaTest {

    @Test
    void createIdea() {
        final Idea idea = new Idea("name", "desc");

        assertThat(idea.name()).isEqualTo("name");
        assertThat(idea.description()).isEqualTo("desc");
    }

    @Test
    void createIdeaWithNameNull() {
        assertThatThrownBy(() -> new Idea(null)).isInstanceOf(TodoRuntimeException.class);
    }

    @Test
    void createIdeaWithDescNull(){
        assertThatThrownBy(() -> new Idea("valid name", null)).isInstanceOf(TodoRuntimeException.class);
    }

    @Test
    void createIdeaWithNameBlank() {
        assertThatThrownBy(() -> new Idea("    ")).isInstanceOf(TodoRuntimeException.class);
    }

    @Test
    void equalIdeas() {
        final Idea abcIdea = new Idea("abc");
        final Idea abcIdea2 = new Idea("abc");

        assertThat(abcIdea).isEqualTo(abcIdea2);
    }

    @Test
    void notEqualIdeas() {
        final Idea abcIdea = new Idea("abc");
        final Idea differentIdea = new Idea("bdc");

        assertThat(abcIdea).isNotEqualTo(differentIdea);
    }

    @Test
    void equalWithDes() {
        final Idea IdeaWithDesc = new Idea("abc", "desc");
        final Idea IdeaWithDesc2 = new Idea("abc", "desc");

        assertThat(IdeaWithDesc).isEqualTo(IdeaWithDesc2);
    }

    @Test
    void notEqualWithDes() {
        final Idea abcIdea = new Idea("abc", "des1");
        final Idea differentIdea = new Idea("abc", "des2");

        assertThat(abcIdea).isNotEqualTo(differentIdea);
    }

    @Test
    void notEqualParameters() {
        final Idea abcIdea = new Idea("abc");
        final Idea differentIdea = new Idea("cde", "des2");

        assertThat(abcIdea).isNotEqualTo(differentIdea);
    }
}
