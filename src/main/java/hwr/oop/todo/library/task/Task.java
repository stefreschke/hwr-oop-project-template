package hwr.oop.todo.library.task;

import hwr.oop.todo.library.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private String title;
    private String description;
    private TaskState state;
    private List<Tag> tags = new ArrayList<>();

    private final UUID id;

    Task(UUID id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
    }

    Task(UUID id, String title) {
        this(id, title, "");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public UUID getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description) && state == task.state && Objects.equals(tags, task.tags) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, state, tags, id);
    }
}
