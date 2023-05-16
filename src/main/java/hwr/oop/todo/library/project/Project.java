package hwr.oop.todo.library.project;

import java.util.Objects;
import java.util.UUID;

public class Project extends ProjectData {
    private UUID id;

    public Project(UUID uuid, String name) {
        super(name);
        this.id = uuid;
    }

    public static Project fromData(UUID uuid, ProjectData data) {
        return new Project(uuid, data.getName());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
