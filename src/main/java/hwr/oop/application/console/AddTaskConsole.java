package hwr.oop.application.console;

import hwr.oop.task.Task;
import hwr.oop.task.TaskBuilder;
import hwr.oop.task.TaskPriority;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddTaskConsole {
    TaskBuilder newTask = new TaskBuilder();
    ConsoleColors colors = new ConsoleColors();
    private final Scanner scanner;
    private final PrintStream printStream;
    private String title = "";
    private String description = "";
    private LocalDateTime deadline;
    private LocalDateTime dateTimePlannedStart;
    private LocalDateTime dateTimePlannedEnd;

    public AddTaskConsole(OutputStream outputStream, InputStream inputStream) {
        this.printStream = new PrintStream(outputStream);
        this.scanner = new Scanner(inputStream);
    }

    public Task startAddNewTask() {
        printStream.println(colors.cyan() + "Add new Task" + colors.reset());
        newTask.setTitle(addNewTitle());
        newTask.setDescription(addNewDescription());
        newTask.setDateTimeDeadline(addNewDeadline());
        newTask.setPriority(addNewPriority());
        newTask.setDateTimePlanned(addTimeStart(), addTimeEnd());

        return newTask.build();
    }

    public String addNewTitle() {
        printStream.println("Please enter the title of the task: ");
        title += scanner.nextLine();
        return title;
    }

    public String addNewDescription() {
        printStream.println("Please enter the description of the task: ");
        description += scanner.nextLine();
        return description;
    }

    public LocalDateTime addNewDeadline() {
        printStream.println("Please enter the deadline of the task(optional)(format yyyy-MM-dd):");
        String deadlineString = scanner.nextLine();
        if (!deadlineString.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(deadlineString, formatter);

            deadline = date.atStartOfDay();
        }
        return deadline;
    }

    public TaskPriority addNewPriority() {
        printStream.println("Please enter the priority of the task(optional)(HIGH, MEDIUM, LOW):");
        String taskPriorityString = scanner.nextLine();
        TaskPriority priority;
        if (taskPriorityString.equals("HIGH")) {
            priority = TaskPriority.HIGH;
        } else if (taskPriorityString.equals("MEDIUM")) {
            priority = TaskPriority.MEDIUM;
        } else if (taskPriorityString.equals("LOW")) {
            priority = TaskPriority.LOW;
        } else {
            priority = TaskPriority.UNDETERMINED;
        }

        return priority;
    }

    public LocalDateTime addTimeStart() {
        printStream.println("Please enter the start time of the task(optional)(format yyyy-MM-dd):");
        String dateTimePlannedStartString = scanner.nextLine();
        if (!dateTimePlannedStartString.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateTimePlannedStartString, formatter);

            dateTimePlannedStart = date.atStartOfDay();
        }

        return dateTimePlannedStart;
    }

    public LocalDateTime addTimeEnd() {
        printStream.println("Please enter the end time of the task(optional)(format yyyy-MM-dd):");
        String dateTimePlannedEndString = scanner.nextLine();
        if (!dateTimePlannedEndString.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateTimePlannedEndString, formatter);

            dateTimePlannedEnd = date.atStartOfDay();
        }

        return dateTimePlannedEnd;
    }
}
