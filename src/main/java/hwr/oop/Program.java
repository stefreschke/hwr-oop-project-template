package hwr.oop;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Program {
    public ToDoList loadToDoList(String fileName) {
        Gson gson = new Gson();
        String json;
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        try (FileReader reader = new FileReader(fileName + ".json")) {
            char[] buffer;
            try {
                buffer = new char[1024];
                int len = reader.read(buffer);
                json = new String(buffer, 0, len);
            } catch (Exception e) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        return gson.fromJson(json, ToDoList.class);
    }

    public String[] getEnvironmentVariables() {
        try (FileReader reader = new FileReader("setup.csv")) {
            char[] buffer;
            try {
                buffer = new char[1024];
                int len = reader.read(buffer);
                String csv = new String(buffer, 0, len);
                return csv.split(",");
            } catch (Exception e) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    public void setEnvironmentVariables(String filePath, String listName) {
        try (FileWriter fileWriter = new FileWriter("setup.csv")) {
            fileWriter.write(filePath + "," + listName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}