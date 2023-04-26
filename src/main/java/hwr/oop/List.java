package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.File.*;
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
        } catch (FileNotFoundException e) {
            File file = new File(fileName + ".json");
            try {
                boolean fileExists = file.createNewFile();
                if (!fileExists) this.writeToJSON(fileName);
                else System.out.println("Sorry...File could not be neither found nor created.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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

    public void sortByPriority(String order) {
        if (order.equals("asc")) {
            for (int i = 0; i < this.ListToDos.length; i++) {
                for (int j = 0; j < this.ListToDos.length - 1; j++) {
                    if (this.ListToDos[j].getPriority().toInt() > this.ListToDos[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.ListToDos[j];
                        this.ListToDos[j] = this.ListToDos[j + 1];
                        this.ListToDos[j + 1] = temp;
                    }
                }
            }
        } else if (order.equals("desc")) {
            for (int i = 0; i < this.ListToDos.length; i++) {
                for (int j = 0; j < this.ListToDos.length - 1; j++) {
                    if (this.ListToDos[j].getPriority().toInt() < this.ListToDos[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.ListToDos[j];
                        this.ListToDos[j] = this.ListToDos[j + 1];
                        this.ListToDos[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleUpTag(String tag) {
        for (int i = this.ListToDos.length-1; i >= 0; i--) {
            for (int j = this.ListToDos.length-1; j > 0; j--) {
                if (this.ListToDos[j].getTag().contains(tag) && !this.ListToDos[j - 1].getTag().contains(tag)) {
                    ToDoItem temp = this.ListToDos[j];
                    this.ListToDos[j] = this.ListToDos[j - 1];
                    this.ListToDos[j - 1] = temp;
                }
            }
        }
    }
    public void sortByCreatedAt(String order) {
        if (order.equals("asc"))
            for (int i = 0; i < this.ListToDos.length; i++) {
                for (int j = 0; j < this.ListToDos.length-1; j++) {
                    if (this.ListToDos[j].getCreatedAt().compareTo(this.ListToDos[j + 1].getCreatedAt()) > 0) {
                        ToDoItem temp = this.ListToDos[j];
                        this.ListToDos[j] = this.ListToDos[j + 1];
                        this.ListToDos[j + 1] = temp;
                    }
                }
            }
        else if (order.equals("desc")) {
            for (int i = 0; i < this.ListToDos.length; i++) {
                for (int j = 0; j < this.ListToDos.length - 1; j++) {
                    if (this.ListToDos[j].getCreatedAt().compareTo(this.ListToDos[j + 1].getCreatedAt()) < 0) {
                        ToDoItem temp = this.ListToDos[j];
                        this.ListToDos[j] = this.ListToDos[j + 1];
                        this.ListToDos[j + 1] = temp;
                    }
                }
            }
        }
    }
}
