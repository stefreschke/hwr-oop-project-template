package hwr.oop.group4.todo.persistence;

public interface PersistenceAdapter {
    void save(Persisted data);

    Persisted load();
}
