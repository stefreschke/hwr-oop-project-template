package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IdeaTest {

    @Test
    void createIdea() {
        final Idea idea = new Idea("name", "desc");

        assertEquals("name", idea.getName());
        assertEquals("desc", idea.getDescription());
    }

    @Test
    void createIdeaWithNull() {
        final Idea idea = new Idea(null);
        final Idea ideaWithDesc = new Idea(null, null);

        assertEquals("", idea.getName());
        assertEquals("", idea.getDescription());

        assertEquals("", ideaWithDesc.getDescription());
        assertEquals("", ideaWithDesc.getDescription());
    }

    @Test
    void setters() {
        final Idea idea = new Idea("name");

        assertEquals("name", idea.getName());
        assertEquals("", idea.getDescription());

        idea.setName("new Name");
        idea.setDescription("new Desc");

        assertEquals("new Name", idea.getName());
        assertEquals("new Desc", idea.getDescription());
    }

    @Test
    void equals() {
        final Idea abcIdea = new Idea("abc");
        final Idea abcIdea2 = new Idea("abc");
        final Idea IdeaWithDesc = new Idea("abc", "desc");
        final Idea IdeaWithDesc2 = new Idea("abc", "desc");
        final Idea differentIdea = new Idea("new Idea", "desc");

        assertEquals(abcIdea, abcIdea2);
        assertEquals(IdeaWithDesc, IdeaWithDesc2);
        assertNotEquals(abcIdea, IdeaWithDesc);
        assertNotEquals(abcIdea, IdeaWithDesc);
        assertNotEquals(abcIdea, differentIdea);
    }
}
