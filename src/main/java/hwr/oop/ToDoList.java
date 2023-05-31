package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ToDoList {
    private String name;
    private List<ToDoItem> items;
    private String fileName;
    private HashSet<Bucket> buckets;

    public ToDoList(String name) {
        this(name, null);
    }
    public ToDoList(String name, String fileName) {
        this.name = name;
        items = new ArrayList<>();
        this.fileName = fileName;
        buckets = new HashSet<>();
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public List<ToDoItem> getItems() {
        return this.items;
    }

    public Set<Bucket> getBuckets() {
        return buckets;
    }

    public void addBucket(String newBucket) {
        this.buckets.add(new Bucket(newBucket));
    }

    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }
    public String getFileName() {
        return this.fileName;
    }

    public void writeToJSON(ConsoleUserInterface cui, String fileName) {
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
                if (!fileExists) this.writeToJSON(cui, fileName);
                else cui.print(LogMode.WARN, "Sorry...File could not be neither found nor created.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void renameBucket (int index, String newBucket) {
        this.buckets.remove(Util.getElementAtIndex(this.buckets, index));
        this.buckets.add(new Bucket(newBucket));
    }
    public void add(ToDoItem toDoItem) {
        this.items.add(toDoItem);
    }
    public void remove(int index) {
        this.items.remove(index);
    }
    public void sortByPriority(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparingInt(o -> o.getPriority().toInt()));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getPriority, Comparator.reverseOrder()));
    }
    public void bubbleUpBucket(String bucket) {
        items.sort((o1, o2) -> {
            if(Objects.equals(o1.getBucket(), o2.getBucket())){
                return 0;
            }
            return Objects.equals(o1.getBucket(), bucket) ? -1 : 1;
        });
    }
    public void sortByCreatedAt(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::getCreatedAt));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getCreatedAt, Comparator.reverseOrder()));
    }
    public void sortByTitle(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::getTitle));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getTitle, Comparator.reverseOrder()));
    }
    public void sortByDone(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::isDone, Comparator.reverseOrder()));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::isDone));
    }
}
