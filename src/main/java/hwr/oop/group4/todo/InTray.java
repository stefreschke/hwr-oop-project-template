package hwr.oop.group4.todo;

import java.util.ArrayList;
import java.util.Collection;

public final class InTray {

    private static final InTray INSTANCE = new InTray();

    private final Collection<Idea> ideas = new ArrayList<>();

    private InTray() {
    }

    public static InTray getInstance() {
         return INSTANCE;
    }

    public void addIdea(Idea idea) {
        ideas.add(idea);
    }

    public Collection<Idea> getIdeas() {
        return ideas;
    }
}
