package hwr.oop.application.console;

import hwr.oop.task.Task;
import hwr.oop.task.TaskBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddTaskConsole {
    TaskBuilder newTask = new TaskBuilder();
    ConsoleColors colors = new ConsoleColors();
    Scanner scan = new Scanner(System.in);
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String deadlineString;

    public Task consoleStart() {
        newTask.setTitle(addTitle());
        newTask.setDescription(addDescription());

        return newTask.build();
    }

    private String addTitle(){
        System.out.print(colors.blue()+"Add New Task\n"+colors.white()+"Please enter the title: " );
        title += scan.nextLine();
        return title;
    }
    private String addDescription(){
        System.out.print("\nPlease enter a description: ");
        description += scan.nextLine();
        return description;
    }
    private LocalDateTime addDateTimeDeadline(){
        System.out.print("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        deadline = LocalDateTime.parse(deadlineString, formatter);
        return null;
    }
}
