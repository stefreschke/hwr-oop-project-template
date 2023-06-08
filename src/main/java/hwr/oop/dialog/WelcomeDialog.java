package hwr.oop.dialog;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WelcomeDialog {
    
    private final ConsoleUserInterface cui;
    private final BufferedReader reader;
    private final String setupFile;
    private final LoadPort loadData;
    public WelcomeDialog(ConsoleUserInterface cui, String setupFile, LoadPort loadData) {
        this.cui = cui;
        this.reader = new BufferedReader(new InputStreamReader(cui.getInputStream()));
        this.setupFile = setupFile;
        this.loadData = loadData;
    }
    public ToDoList start() throws CannotLaunchSetupException {
        this.cui.say("Welcome To Getting Things Done 🚀");
        String[] env = PersistenceAdapter.getEnvironmentVariables(this.setupFile);
        if (env.length != 2) {
            try {
                return firstTimeSetup();
            } catch (IOException e) {
                throw new CannotLaunchSetupException();
            }
        } else {
            return envLoadSuccessful(env);
        }
    }
    
    public ToDoList firstTimeSetup() throws IOException, CannotLaunchSetupException {
        String listName;
        this.cui.say("Looks Like it is your first time using this program.");
        this.cui.say("Lets set you up first.");
        this.cui.say("Please enter a name for your list");
        this.cui.say("> ");
        listName = reader.readLine();
        this.cui.say("Please provide a filePath to an existing .json file to Load your list from.");
        this.cui.say("If you don't have one press enter to create specify your path.");
        this.cui.say("> ");
        String filePath = reader.readLine();
        while (filePath == null || !filePath.endsWith(".json")) {
            this.cui.say("Please provide a filePath to an existing .json file to Load your list from.");
            this.cui.say("If you don't have one press enter to create specify your path.");
            this.cui.say("> ");
            filePath = reader.readLine();
            if (filePath.trim().equals("")) {
                return makeNewFile(listName);
            }
        }
        try {
            return takeExistingFile(filePath, listName);
        } catch (CannotLaunchSetupException e) {
            throw new CannotLaunchSetupException();
        }
    }
    public ToDoList envLoadSuccessful(String[] env) {
        String listFileName = env[0];
        String listName = env[1];
        if (listFileName.contains(".")) {
            listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
        }
        ToDoList toDoList = loadData.loadData();
        if (toDoList == null) {
            return new ToDoList(listName, listFileName);
        } else {
            return toDoList;
        }
    }
    public ToDoList takeExistingFile(String filePath, String listName) throws CannotLaunchSetupException {
        String listFileName;
        if (filePath.contains(".")) {
            listFileName = filePath.substring(0, filePath.lastIndexOf('.'));
        } else {
            listFileName = filePath;
        }
        PersistenceAdapter.setEnvironmentVariables(listFileName, listName, this.setupFile);
        return start();
    }
    public ToDoList makeNewFile(String listName) throws IOException {
        String listFileName;
        this.cui.say("Please enter your a path to a file to save your list to.");
        this.cui.say("> ");
        listFileName = reader.readLine();
        if (listFileName.contains(".")) {
            listFileName = listFileName.substring(0, listFileName.lastIndexOf('.'));
        }
        PersistenceAdapter.setEnvironmentVariables(listFileName, listName, this.setupFile);
        return new ToDoList(listName, listFileName);
    }

    public static class CannotLaunchSetupException extends Exception {
        public CannotLaunchSetupException() {
            super("Cannot Launch Setup");
        }
    }
}
