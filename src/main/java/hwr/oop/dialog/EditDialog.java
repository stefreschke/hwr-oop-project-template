package hwr.oop.dialog;

import hwr.oop.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class EditDialog {
    private final ToDoList toDoList;
    private final ConsoleUserInterface cui;
    private final PrintStream out;
    private final BufferedReader reader;
    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.MM.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.M.yyyy"))

            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.MM.yy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yy"))
            .appendOptional(DateTimeFormatter.ofPattern("d.M.yy"))
            .toFormatter();

    public EditDialog(ConsoleUserInterface cui, ToDoList toDoList) {
        this.toDoList = toDoList;
        this.cui = cui;
        this.out = cui.getOutputStream();
        this.reader = new BufferedReader(new InputStreamReader(cui.getInputStream()));
    }
    public ToDoItem start(ToDoItem item, int index) throws ConsoleUserInterface.CouldNotReadInputException {
        cui.print(LogMode.NONE,"Editing task at index " + index + ":");
        cui.print(LogMode.NONE,item.toString());
        String title = getTitleForEdit(item);
        String description = getDescriptionForEdit(item);
        Priority priority = getPriorityForEdit(item);
        Bucket bucket = getBucketForEdit(item);
        LocalDate dueDate = getDueDateForEdit(item);
        return new ToDoItem(
                title,
                description,
                bucket,
                priority,
                dueDate
        );
    }
    public  String getTitleForEdit(ToDoItem item) throws ConsoleUserInterface.CouldNotReadInputException {
        this.out.println("Enter new Title or press enter to skip");
        String title;
        try {
            title = this.reader.readLine();
            if (!title.equals("")) return title;
            else return item.getTitle();
        } catch (Exception e) {
            throw new ConsoleUserInterface.CouldNotReadInputException();
        }
    }
    public  String getDescriptionForEdit(ToDoItem item) throws ConsoleUserInterface.CouldNotReadInputException {
        this.out.println("Enter new Description or press enter to skip");
        String description;
        try {
            description = this.reader.readLine();
            if (!description.equals("")) return description;
            else return item.getDescription();
        } catch (Exception e) {
            throw new ConsoleUserInterface.CouldNotReadInputException();
        }
    }
    public Priority getPriorityForEdit(ToDoItem item) throws ConsoleUserInterface.CouldNotReadInputException {
        this.out.println("Enter new Priority or press enter to skip");
        this.out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3") && !priority.equals("")) {
            try {
                priority = this.reader.readLine();
            } catch (IOException e) {
                throw new ConsoleUserInterface.CouldNotReadInputException();
            }
        }
        if (!priority.equals("")) {
            int prioInt = Integer.parseInt(priority);
            return Priority.fromInt(prioInt);
        }
        return item.getPriority();
    }
    public Bucket getBucketForEdit(ToDoItem item) {
        this.out.println("Enter new Bucket or press enter to skip");
        String bucket;
        try {
            bucket = this.reader.readLine();
            if (!bucket.equals("")) {
                Bucket newBucket = this.toDoList.findBucket(bucket);
                return newBucket == null ? new Bucket(bucket) : newBucket;
            } else return item.getBucket();
        } catch (IOException e) {
            this.out.println("Could not read your input... skipping");
        }
        return item.getBucket();
    }
    public LocalDate getDueDateForEdit(ToDoItem item) {
        out.println("Enter new Due Date or press enter to skip");
        try {
            String line = this.reader.readLine();
            if (line.equals("")) return item.getDueDate();
            return LocalDate.parse(line, this.formatter);
        } catch (Exception e) {
            this.out.println("Could not read your input... skipping");
        }
        return item.getDueDate();
    }
    public void end() {
        cui.print(LogMode.SUCCESS, "Task Edited Successfully!");
    }
}
