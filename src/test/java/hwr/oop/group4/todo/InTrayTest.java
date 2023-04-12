package hwr.oop.group4.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InTrayTest {

    @Test
    void isSingleton() {
        final InTray inTrayA = InTray.getInstance();
        final InTray inTrayB = InTray.getInstance();

        assertEquals(inTrayA, inTrayB);
        assertEquals(0, inTrayA.getIdeas().size());
        assertEquals(0, inTrayB.getIdeas().size());

        final Idea idea = new Idea("idea name", "description");
        inTrayA.addIdea(idea);

        assertTrue(inTrayA.getIdeas().contains(idea));
        assertTrue(inTrayB.getIdeas().contains(idea));
    }
}