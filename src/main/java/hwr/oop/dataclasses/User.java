package hwr.oop.dataclasses;

public class User {
    public User(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    private final String name;
    private final Integer id;
}