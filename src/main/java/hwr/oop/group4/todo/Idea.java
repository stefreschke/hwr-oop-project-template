package hwr.oop.group4.todo;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;

import java.util.Objects;

public class Idea {

    private final String name;
    private final String description;

    public Idea(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new TodoRuntimeException("Ideas need non blank names.");
        }
        if (description == null) {
            throw new TodoRuntimeException("Description should not be null.");
        }
        this.name = name;
        this.description = description;
    }

    public Idea(String name) {
        this(name, "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idea idea = (Idea) o;
        return Objects.equals(name, idea.name) && Objects.equals(description, idea.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
