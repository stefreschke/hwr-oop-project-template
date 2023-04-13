package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String title;
    private String description;
    private TaskState state;
    private List<String> tags = new ArrayList<>();
    private TagController tagController = TagController.get();

    public Task(String title, String description){
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
    }

    public Task(String title) {
        this(title, "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        List<String> knownTags = this.tagController.getTags();
        if (!knownTags.contains(tag)){
            tagController.createTag(tag);
        }
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }
}
