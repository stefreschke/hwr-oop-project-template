package hwr.oop.todo.ui;

import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.responses.StringResponse;

import java.util.UUID;

public class Menus {
    public static final Menu HOME = new Menu()
            .on('a', "Navigate to Home").navigateTo(Menus.HOME)
            .on('b', "Print 'Hello World'").printString("Hello World!")
            .on('c', "Add task").execute((toDoList, parameters) -> {
                String title = parameters.getParameter("Titel");
                String description = parameters.getParameter("Beschreibung");

                Task task = TaskFactory.createTask(title, description);
                toDoList.addTask(task);

                return StringResponse.with("Successfully created task: "+task.getId());
            })
            .on('d', "Get task by ID").execute((toDoList, parameters) -> {
                String sId = parameters.getParameter("ID");
                UUID id = UUID.fromString(sId);

                Task task = toDoList.getTask(id);

                return StringResponse.with(
                        "Titel: "+task.getTitle()+"\n\r"+
                                "Beschreibung: "+task.getDescription()
                );
            });

     private Menus() {
    }
}
