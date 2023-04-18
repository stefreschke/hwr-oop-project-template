package hwr.oop.todo;

import java.util.Objects;

public class Tag {
    private String name;
    private String description;

    public Tag(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Tag(String name){
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
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name) && Objects.equals(description, tag.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
