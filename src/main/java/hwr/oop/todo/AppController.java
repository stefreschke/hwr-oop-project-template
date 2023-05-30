package hwr.oop.todo;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.io.IOController;
import hwr.oop.todo.ui.MenuController;

public class AppController {
    private static final ToDoList toDoList = new ToDoList();

    private static final IOController io = new IOController(System.in, System.out);

    private static final MenuController ui = new MenuController(toDoList, io);

    public static void main(String[] args){
        ui.execute();
    }

}
