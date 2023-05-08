package hwr.oop.group4.todo.persistence;

import hwr.oop.group4.todo.core.TodoList;
import org.json.JSONObject;

public class PersistableTodoList implements Persistable {
    private final TodoList todoList;

    public PersistableTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @Override
    public String toString() {
        JSONObject todoListObject = new JSONObject(todoList);
        return todoListObject.toString();
    }
}
