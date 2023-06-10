package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.util.LocalDateTypeAdapter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Program {
    public ToDoList loadToDoList(String fileName) {
        ToDoList toDoList = getToDoListFromJSON(fileName);
        if (toDoList != null) {
            if (toDoList.getItems() == null) {
                toDoList.setItems(new ArrayList<>());
            }
            linkToCorrectBucket(toDoList);
        }
        return toDoList;
    }
    private ToDoList getToDoListFromJSON(String fileName) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String json;
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".json"))){
            json = br.readLine();
        } catch (IOException e) {
            return null;
        }
        return gson.fromJson(json, ToDoList.class);
    }
    public String[] getEnvironmentVariables(String file) {
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
    public void setEnvironmentVariables(String filePath, String listName, String fileName) {
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
    private void linkToCorrectBucket(ToDoList toDoList) {
        for (ToDoItem item:toDoList.getItems()) {
            for (Bucket bucket:toDoList.getBuckets()) {
                if (item.getBucket().getBucketName().equals(bucket.getBucketName())) {
                    item.setBucket(bucket);
                }
            }
            toDoList.getBuckets().add(item.getBucket());
        }
    }
}