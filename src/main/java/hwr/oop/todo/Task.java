package hwr.oop.todo;

import java.util.Objects;
import java.util.UUID;

public class Task extends TaskData {
    private final UUID id;

    public Task(UUID uuid, String title, String description){
        super(title, description);
        this.id = uuid;
    }

    public static Task fromData(UUID uuid, TaskData data){
        return new Task(uuid, data.getTitle(), data.getDescription());
    }

    public UUID getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
