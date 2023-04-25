package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class List {
    private String Name;
    private ToDoItem[] ListToDos;

    public List(String name) {
        this.Name = name;
        this.ListToDos = new ToDoItem[0];
    }

    public void setName(String name) {
        this.Name = name;
    }
    public String getName() {
        return this.Name;
    }

    public ToDoItem[] getListToDos() {
        return this.ListToDos;
    }

    public void setListToDos(ToDoItem[] listToDos) {
        this.ListToDos = listToDos;
    }
    public void writeToJSON(String fileName) {
        //remove any file extension if present
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this);

        try (FileWriter fileWriter = new FileWriter(fileName + ".json")) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(ToDoItem toDoItem) {
        ToDoItem[] newList = new ToDoItem[this.ListToDos.length + 1];
        System.arraycopy(this.ListToDos, 0, newList, 0, this.ListToDos.length);
        newList[newList.length - 1] = toDoItem;
        this.ListToDos = newList;
    }

    public void remove(int index) {
        ToDoItem[] newList = new ToDoItem[this.ListToDos.length - 1];
        System.arraycopy(this.ListToDos, 0, newList, 0, index);
        System.arraycopy(this.ListToDos, index + 1, newList, index, this.ListToDos.length - 1);
        this.ListToDos = newList;
    }
}
