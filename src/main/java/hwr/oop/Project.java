package hwr.oop;

import java.util.List;

public class Project {
    public String title;
    public List<ToDoItem> toDoList;

    public void setTitle(String title) {
        this.title = title;
    }
    public void setList(List<ToDoItem> toDoList) {
        this.toDoList = toDoList;
    }
}
