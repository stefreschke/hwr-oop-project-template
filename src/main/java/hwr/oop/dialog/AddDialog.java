package hwr.oop.dialog;

import hwr.oop.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class AddDialog {
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
    private static final String PLEASE_EDIT_LATER_USING_GTD_EDIT_INDEX = "Please edit later using gtd edit [index]";
    public AddDialog(ConsoleUserInterface cui, ToDoList toDoList) {
        this.toDoList = toDoList;
        this.cui = cui;
        this.out = cui.getOutputStream();
        InputStream in = cui.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(in));
    }
    public ToDoItem start() throws ConsoleUserInterface.CouldNotReadInputException {
        cui.say("Create a new task");
        String title = getTitleForAdd();
        String description = getDescriptionForAdd();
        Priority priority = getPriorityForAdd();
        Bucket bucket = getBucketForAdd();
        LocalDate dueDate = getDueDateForAdd();
        return new ToDoItem(
                title,
                description,
                bucket,
                priority,
                dueDate
        );
    }
    public String getTitleForAdd() {
        out.println("Please enter a title for your task");
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO TITLE";
    }
    public String getDescriptionForAdd() {
        out.println("Please enter a description for your task");
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            this.cui.say("Could not read input. We will set a standard description.");
            this.cui.say(PLEASE_EDIT_LATER_USING_GTD_EDIT_INDEX);
        }
        return "NO DESCRIPTION";
    }
    public Priority getPriorityForAdd() throws ConsoleUserInterface.CouldNotReadInputException {
        out.println("Please select a priority for your task");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3")) {
            try {
                priority = this.reader.readLine();
            } catch (IOException e) {
                throw new ConsoleUserInterface.CouldNotReadInputException();
            }
        }
        return Priority.fromInt(Integer.parseInt(priority));
    }
    public Bucket getBucketForAdd() {
        out.println("Add a Bucket to group your tasks");
        try {
            String bucketName = this.reader.readLine();
            if (this.toDoList.findBucket(bucketName) != null) {
                return this.toDoList.findBucket(bucketName);
            }
            return new Bucket(bucketName);
        } catch (IOException e) {
            this.cui.say("Could not read input. We will set a standard bucket.");
            this.cui.say(PLEASE_EDIT_LATER_USING_GTD_EDIT_INDEX);
        }
        return new Bucket("NO BUCKET");
    }
    public LocalDate getDueDateForAdd() {
        out.println("Please enter a due date for your task");
        try {
            return LocalDate.parse(this.reader.readLine(), formatter);
        } catch (IOException e) {
            this.cui.say("Could not read input. We will set the due date to tomorrow.");
            this.cui.say(PLEASE_EDIT_LATER_USING_GTD_EDIT_INDEX);
        }
        return LocalDate.now().plusDays(1);
    }
    public void end() {
        cui.print(LogMode.SUCCESS, "Task Created Successfully!🎉");
    }
}