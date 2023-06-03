package hwr.oop.application;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private List<String> inbox;

    private List<Task> contextList;

    public User(String name, List<String> inbox, List<Task> contextList) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.inbox = inbox;
        this.contextList = contextList;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public List<String> getInbox() {
        return inbox;
    }

    public List<Task> getContextList() {
        return contextList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getInbox(), user.getInbox()) &&
                Objects.equals(getContextList(), user.getContextList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getInbox(), getContextList());
    }
}