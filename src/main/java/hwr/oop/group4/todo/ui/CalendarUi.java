package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.ConsoleHelper;
import hwr.oop.group4.todo.ui.controller.menu.Entry;
import hwr.oop.group4.todo.ui.controller.menu.EntryArgument;
import hwr.oop.group4.todo.ui.controller.menu.Menu;
import hwr.oop.group4.todo.core.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUi {
    private final ConsoleController consoleController;
    private final ConsoleHelper consoleHelper;
    private TodoList todoList;
    private final String[] daysOfWeek = {"Mo","Di","Mi","Do","Fr","Sa","So"};

    public CalendarUi(ConsoleController consoleController){
        this.consoleController = consoleController;
        this.consoleHelper = new ConsoleHelper();
    }
    public void menu(TodoList todoList){ //enough entries?
        this.todoList = todoList;
        Menu menu = new Menu("Calendar Menu", "See your current Calendar?", List.of(
            new Entry("next week", "Scroll to the next week"),
            new Entry("last week", "Scroll one week back"),
            new Entry("back", "Return to the previous menu")
        ));
        consoleController.output(menu.toString());
    }

    private void createTable(){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (String day : daysOfWeek) {
            System.out.print(String.format("%10s", day));
        }
        System.out.println();
        for (int i = 0; i < 7; i++){ //create with table class
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.print(String.format("%10d", day));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println();
    }

    //create 2 funcs for scrolling through different weeks?(+/- 7d)

    private void assignTaskToCalendar(Task task){
        //get deadline from a task and assign it to the date
        //while 1
        // Task.getDate
        // if deadline = currentDate
        // add to day
    }
}
