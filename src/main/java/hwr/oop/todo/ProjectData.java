package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProjectData {
    private String name;
    private List<UUID> tasks = new ArrayList<>();

    public ProjectData(String name){
        this.name = name;
    }

    public void addTask(UUID id){
        tasks.add(id);
    }

    public void addTask(Task task){
        tasks.add(task.getId());
    }

    public List<UUID> getTaskIds(){
        return tasks;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectData that = (ProjectData) o;
        return Objects.equals(name, that.name) && Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tasks);
    }
}
