package hwr.oop.group4.todo.core;

import hwr.oop.group4.todo.commons.exceptions.TodoRuntimeException;

public record Idea(String name, String description) {

    public Idea {
        if (name == null || name.isBlank()) {
            throw new TodoRuntimeException("Ideas need non blank names.");
        }
        if (description == null) {
            throw new TodoRuntimeException("Description should not be null.");
        }
    }

    public Idea(String name) {
        this(name, "");
    }

}
