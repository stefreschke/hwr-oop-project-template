package hwr.oop.group4.todo.persistence;

public interface PersistenceAdapter<T extends Persisted> {
    void save(T data);
    T load();
}
