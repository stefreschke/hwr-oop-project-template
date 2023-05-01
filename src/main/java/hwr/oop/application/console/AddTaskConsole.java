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
    private String description;
    private LocalDateTime deadline;
    private String deadlineString;
    private TaskPriority priority;
    private String TaskPriorityString;

    public AddTaskConsole(OutputStream outputStream, InputStream inputStream) {
        this.printStream = new PrintStream(outputStream);
        this.scanner = new Scanner(inputStream);
    }

    public String AddNewTitle() {
        printStream.println("Please enter the title of the task: ");
        title += scanner.nextLine();
        scanner.close();
        return title;
    }
}
