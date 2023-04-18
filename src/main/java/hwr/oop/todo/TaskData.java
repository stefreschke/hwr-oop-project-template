package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskData {
    private String title;
    private String description;
    private TaskState state;
    private List<Tag> tags = new ArrayList<>();

    public TaskData(String title, String description){
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
    }

    public TaskData(String title) {
        this(title, "");
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskData taskData = (TaskData) o;
        return title.equals(taskData.title) && description.equals(taskData.description) && state == taskData.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, state);
    }
}
