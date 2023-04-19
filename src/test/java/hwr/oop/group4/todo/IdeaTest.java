package hwr.oop.group4.todo;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IdeaTest {

    @Test
    void createIdea() {
        final Idea idea = new Idea("name", "desc");

        assertThat(idea.getName()).isEqualTo("name");
        assertThat(idea.getDescription()).isEqualTo("desc");
    }

    @Test
    void createIdeaWithNull() {
        assertThatThrownBy(() -> new Idea(null)).isInstanceOf(TodoRuntimeException.class);
    }
    @Test
    void createIdeaWithDesTwoNull(){
        assertThatThrownBy(() -> new Idea(null, null)).isInstanceOf(TodoRuntimeException.class);
    }

    @Test
    void equal() {
        final Idea abcIdea = new Idea("abc");
        final Idea abcIdea2 = new Idea("abc");

        assertThat(abcIdea).isEqualTo(abcIdea2);
    }
    @Test
    void notEqual(){
        final Idea abcIdea = new Idea("abc");
        final Idea differentIdea = new Idea("bdc");

        assertThat(abcIdea).isNotEqualTo(differentIdea);
    }
    @Test
    void equalWithDes(){
        final Idea IdeaWithDesc = new Idea("abc", "desc");
        final Idea IdeaWithDesc2 = new Idea("abc", "desc");

        assertThat(IdeaWithDesc).isEqualTo(IdeaWithDesc2);
    }

    @Test
    void notEqualWithDes(){
        final Idea abcIdea = new Idea("abc", "des1");
        final Idea differentIdea = new Idea("abc", "des2");

        assertThat(abcIdea).isNotEqualTo(differentIdea);
    }

    @Test
    void notEqualParameters(){
        final Idea abcIdea = new Idea("abc");
        final Idea differentIdea = new Idea("cde", "des2");

        assertThat(abcIdea).isNotEqualTo(differentIdea);
    }
}
