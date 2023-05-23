package hwr.oop.application;

import java.util.List;
import java.util.UUID;

public class User {
    private final String name;
    private final UUID id;
    private List<String> inbox;

    private List<Task> contextList;

    public User(String name, UUID id, List<String> inbox, List<Task> contextList) {
        this.name = name;
        this.id = id;
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

}