package hwr.oop.todo.library.project;

import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class ProjectController {
    private final ToDoList todolist = new ToDoList();
    private static final ProjectController self = new ProjectController();

    public static ProjectController get() {
        return self;
    }

    public ToDoList getToDoList() {
        return todolist;
    }

    public Project createProject(ProjectData projectdata){
        return todolist.createProject(projectdata);
    }

    public Project getProject(UUID id){
        return todolist.getProject(id);
    }
}
