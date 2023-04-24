package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class List {
    private String Name;
    private ToDoItem[] ListToDos;


    String json;

    public void setName(String name) {
        this.Name = name;
    }
    public String getName() {
        return this.Name;
    }

    public void writeToJSON() {
        Gson gson = new Gson();
        this.json = gson.toJson(this);
        try {
            // später kann der User seinen Speicherort selber wählen (?)
            gson.toJson(123.45, new FileWriter("./output.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createToDo(String description, String title, boolean done, Priority priority) {
        ToDoItem Todo = new ToDoItem();
        Todo.setTitle(title);
        Todo.setDescription(description);
        Todo.setDone(done);
        Todo.setPriority(priority);
    }
}