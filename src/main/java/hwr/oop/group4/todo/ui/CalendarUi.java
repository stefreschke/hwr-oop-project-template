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
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
            new Entry("back", "Return to the previous menu \n")
        ));
        consoleController.output(menu.toString());

        AtomicBoolean shouldReturn = new AtomicBoolean(false);
        while (!shouldReturn.get()) {
            assignProjectToCalendar(date);
            consoleController.inputOptions(List.of("calendar"), List.of(
                    new Command("today", args -> date = LocalDate.now()),
                    new Command("nextWeek", args -> date = date.plusWeeks(1)),
                    new Command("lastWeek", args -> date = date.minusWeeks(1)),
                    new Command("back", args -> shouldReturn.set(true))
            ), new Command("wrongInput", args -> {}));
        }
    }

    private void assignProjectToCalendar(LocalDate date) {
        LocalDate monday = date.minusDays(date.getDayOfWeek().getValue() - 1);
        final Table calendarTable = createCalendarHeader(monday);
        todoList.getProjects().forEach(project -> calendarTable.addRow(createCalendarRow(monday, project)));
        consoleController.output(calendarTable.toString());
    }

    private Table createCalendarHeader(LocalDate monday) {
        final List<ColumnConfig> header = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate day = monday.plusDays(i);
            header.add(new ColumnConfig(day.getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.US) +
                    " - " +
                    day.format(DateTimeFormatter.ofPattern("dd.MM.yy")), 15));
        }
        return new Table(header);
    }

    private String[] createCalendarRow(LocalDate monday, Project project) {
        String[] projectRuntime = new String[7];
        for (int o = 0; o < 7; o++) {
            LocalDate day = monday.plusDays(o);
            if ((day.isBefore(project.getEnd().toLocalDate()) || day.isEqual(project.getEnd().toLocalDate())) &&
                    (day.isAfter(project.getBegin().toLocalDate()) || day.isEqual(project.getBegin().toLocalDate()))) {
                projectRuntime[o] = project.getName();
            } else {
                projectRuntime[o] = "";
            }
        }
        return projectRuntime;
    }
}
