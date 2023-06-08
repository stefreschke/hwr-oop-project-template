package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.ToDoList;
import hwr.oop.util.LocalDateTypeAdapter;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static hwr.oop.ToDoList.linkToCorrectBucket;

public class PersistenceAdapter implements SavePort, LoadPort {
    private final String fileName;
    public PersistenceAdapter(String fileName) {
        this.fileName = fileName;
    }
    public static String[] getEnvironmentVariables(String file) {
        if (file.contains(".")) {
            file = file.substring(0, file.lastIndexOf('.'));
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file + ".csv"))){
            String line;
            if ((line = br.readLine()) != null) {
                String delimiter = ",";
                return line.split(delimiter);
            }
            return new String[0];
        } catch (FileNotFoundException e) {
            return new String[0];
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
    public static void setEnvironmentVariables(String filePath, String listName, String fileName) {
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        try (FileWriter fileWriter = new FileWriter(fileName + ".csv")) {
            fileWriter.write(filePath + "," + listName);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(ToDoList toDoList) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter())
                .create();
        try (FileWriter fileWriter = new FileWriter(fileName + ".json")) {
            gson.toJson(toDoList, fileWriter);
        } catch (IOException e) {
            throw new FileNotFoundException("Sorry...File could not be neither found nor created.");
        }
    }

    @Override
    public ToDoList loadData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String json;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName.substring(0, fileName.lastIndexOf('.')) + ".json"))){
            json = br.readLine();
        } catch (IOException e) {
            return null;
        }
        ToDoList toDoList = gson.fromJson(json, ToDoList.class);
        if (toDoList != null) {
            if (toDoList.getItems() == null) {
                toDoList.setItems(new ArrayList<>());
            }
            linkToCorrectBucket(toDoList);
        }
        return toDoList;
    }
}