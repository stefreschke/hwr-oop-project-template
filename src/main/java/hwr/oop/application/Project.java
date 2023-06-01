package hwr.oop.application;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Project {
    public Project(List<Task> taskList, String title, Map<User, Boolean> permissions) {
        this.id = UUID.randomUUID();
        this.taskList = taskList;
        this.title = title;
        this.permissions = permissions;
    }

    public UUID getId() {
        return id;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String getTitle() {
        return title;
    }

    public Map<User, Boolean> getPermissions() {
        return permissions;
    }

    private final UUID id;
    private List<Task> taskList;
    private String title;
    private Map<User, Boolean> permissions;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Project) {
            return (Objects.equals(((Project) obj).id, this.id));
        }
        return false;
    }

}
