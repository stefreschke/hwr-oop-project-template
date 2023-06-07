package hwr.oop.dialog;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.Priority;
import hwr.oop.ToDoItem;

import java.io.*;

public class AddDialog {
    private final ConsoleUserInterface cui;
    private final PrintStream out;
    private final BufferedReader reader;
    public AddDialog(ConsoleUserInterface cui) {
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
        String bucket = getBucketForAdd();
        return new ToDoItem(
                title,
                description,
                bucket,
                priority
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
            e.printStackTrace();
        }
        return "NO DESCRIPTION";
    }
    public Priority getPriorityForAdd() throws ConsoleUserInterface.CouldNotReadInputException {
        out.println("Please select a priority for your task");
        out.println("1 - LOW, 2 - MEDIUM, 3 - HIGH");
        String priority = "-1";
        while (!priority.equals("1") && !priority.equals("2") && !priority.equals("3")) {
            try {
                priority = reader.readLine();
            } catch (IOException e) {
                throw new ConsoleUserInterface.CouldNotReadInputException();
            }
        }
        return Priority.fromInt(Integer.parseInt(priority));
    }
    public String getBucketForAdd() {
        out.println("Add a Bucket to group your tasks");
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO BUCKET";
    }
    public void end() {
        cui.print(LogMode.SUCCESS, "Task Created Successfully!🎉");
    }
}
