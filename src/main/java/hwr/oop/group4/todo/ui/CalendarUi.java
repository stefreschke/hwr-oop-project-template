package hwr.oop.group4.todo.ui;

import hwr.oop.group4.todo.core.Project;
import hwr.oop.group4.todo.core.TodoList;
import hwr.oop.group4.todo.ui.controller.ConsoleController;
import hwr.oop.group4.todo.ui.controller.ConsoleHelper;
import hwr.oop.group4.todo.ui.controller.command.Command;
import hwr.oop.group4.todo.ui.controller.menu.Entry;
import hwr.oop.group4.todo.ui.controller.menu.Menu;
import hwr.oop.group4.todo.ui.controller.tables.ColumnConfig;
import hwr.oop.group4.todo.ui.controller.tables.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalendarUi {
    private final ConsoleController consoleController;
    private final ConsoleHelper consoleHelper;
    private TodoList todoList;
    private LocalDate date = LocalDate.now();

    public CalendarUi(ConsoleController consoleController){
        this.consoleController = consoleController;
        this.consoleHelper = new ConsoleHelper();
    }
    public void menu(TodoList todoList){
        this.todoList = todoList;
        Menu menu = new Menu("Calendar Menu", "", List.of(
            new Entry("today", ""),
            new Entry("nextWeek", ""),
            new Entry("lastWeek", ""),
            new Entry("back", "Return to the previous menu")
        ));
        consoleController.output(menu.toString());

        AtomicBoolean shouldReturn = new AtomicBoolean(false);
        while (!shouldReturn.get()) {
            createTable(date);
            consoleController.inputOptions(List.of("calendar"), List.of(
                    new Command("today", args -> date = LocalDate.now()),
                    new Command("nextWeek", args -> date = date.plusWeeks(1)),
                    new Command("lastWeek", args -> date = date.minusWeeks(1)),
                    new Command("back", args -> shouldReturn.set(true))
            ), new Command("wrongInput", args -> {}));
        }
    }

    //new ColumnConfig(day.getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.US), 10),
    //String dayOfMonth = String.valueOf(day.getDayOfMonth());
    //LocalDate day = monday.plusDays(i);
    private void createTable(LocalDate date){
        LocalDate monday = date.minusDays(date.getDayOfWeek().getValue() - 1);

        final Table dateTable = new Table(List.of(//center the date
                new ColumnConfig(monday.format(DateTimeFormatter.ofPattern("dd.MM.YY")) +
                        "      -      " +
                        monday.plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.YY")),
                        123)//I don't understand len of Table
        ));
        consoleController.output(dateTable.toString());

        final Table calendarTable = new Table(List.of(
                new ColumnConfig("Mon", 15),
                new ColumnConfig("Tue", 15),
                new ColumnConfig("Wed", 15),
                new ColumnConfig("Thu", 15),
                new ColumnConfig("Fri", 15),
                new ColumnConfig("Sat", 15),
                new ColumnConfig("Sun", 15)
        ));
        consoleController.output(calendarTable.toString());
        assignProjectToCalendar(date);
    }

    private void assignProjectToCalendar(LocalDate date) {
        this.date = date;
        LocalDate monday = date.minusDays(date.getDayOfWeek().getValue() - 1);
        final List<Project> projects = todoList.getProjects();

        for (int i = 0; i < projects.size(); i++) {
            final Project project = projects.get(i);

            for (int o = 0; o < 7; o++) {
                LocalDate day = monday.plusDays(o);
                String days = day.format(DateTimeFormatter.ofPattern("dd.MM.yy"));

                if (days.equals(project.getEnd().format(DateTimeFormatter.ofPattern("dd.MM.yy")))) {
                    consoleController.outputLine(project.getName()+ "\n" + project.getDescription() + "\n" + project.getEnd().format(DateTimeFormatter.ofPattern("dd.MM.yy")));
                }
            }
        }
    }
}
