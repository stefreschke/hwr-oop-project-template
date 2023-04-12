package hwr.oop.group4.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}