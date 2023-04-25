package hwr.oop.group4.todo.persistence;

import hwr.oop.group4.todo.TodoList;
import org.json.JSONObject;

public class PersistedTodoList implements Persisted {
    private final TodoList todoList;

    public PersistedTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @Override
    public String toString() {
        JSONObject todoListObject = new JSONObject(todoList);
        return todoListObject.toString();
    }
}
