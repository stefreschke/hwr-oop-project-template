package hwr.oop.dialog;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.LogMode;
import hwr.oop.Priority;
import hwr.oop.ToDoItem;

import java.io.*;

public class EditDialog {
    private final ConsoleUserInterface cui;
    private final PrintStream out;
    private final BufferedReader reader;
    public EditDialog(ConsoleUserInterface cui) {
        this.cui = cui;
        this.out = cui.getOutputStream();
        this.reader = new BufferedReader(new InputStreamReader(cui.getInputStream()));
    }
    public ToDoItem start(ToDoItem item, int index) throws ConsoleUserInterface.CouldNotReadInputException {
        cui.say("Editing task at index " + index + ":");
        cui.say(item.toString());
        String title = getTitleForEdit(item);
        String description = getDescriptionForEdit(item);
        Priority priority = getPriorityForEdit(item);
        String bucket = getBucketForEdit(item);
        return new ToDoItem(
                title,
                description,
                bucket,
                priority
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
    public String getBucketForEdit(ToDoItem item) {
        this.out.println("Enter new Bucket or press enter to skip");
        String bucket;
        try {
            bucket = this.reader.readLine();
            if (!bucket.equals("")) return bucket;
            else return item.getBucket();
        } catch (IOException e) {
            this.out.println("Could not read your input... skipping");
        }
        return item.getBucket();
    }
    public void end() {
        cui.print(LogMode.SUCCESS, "Task Edited Successfully!");
    }
}
