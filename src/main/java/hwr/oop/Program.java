package hwr.oop;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public ToDoList loadToDoList(String fileName) {
        ToDoList toDoList = getToDoListFromJSON(fileName);
        if (toDoList != null && toDoList.getItems() == null) {
            toDoList.setItems(new ArrayList<>());
            return toDoList;
        }
        return toDoList;
    }


    private ToDoList getToDoListFromJSON(String fileName) {
        Gson gson = new Gson();
        String json;
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        try (FileReader reader = new FileReader(fileName + ".json")) {
            char[] buffer;
            buffer = new char[1024];
            int len = reader.read(buffer);
            json = new String(buffer, 0, len);
        } catch (IOException e) {
            return null;
        }
        return gson.fromJson(json, ToDoList.class);
    }
    public String[] getEnvironmentVariables(String file) {
        try (FileReader reader = new FileReader(file + ".csv")){
            char[] buffer;
            buffer = new char[1024];
            int len = reader.read(buffer);
            String csv = new String(buffer, 0, len);
            return csv.split(",");
        } catch (FileNotFoundException e) {
            return new String[]{"FILE_NOT_FOUND", "FILE_NOT_FOUND"};
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public void setEnvironmentVariables(String filePath, String listName, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName + ".csv")) {
            fileWriter.write(filePath + "," + listName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}