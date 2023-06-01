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
        this.name = name;
        this.id = UUID.randomUUID();
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
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return (Objects.equals(((User) obj).id, this.id));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,inbox,contextList);
    }
}