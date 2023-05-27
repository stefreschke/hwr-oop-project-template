package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ToDoList {
    private String name;
    private ToDoItem[] items;
    private String fileName;
    private HashSet<Bucket> buckets;

    public ToDoList(String name) {
        this(name, null);
    }
    public ToDoList(String name, String fileName) {
        this.name = name;
        this.items = new ToDoItem[0];
        this.fileName = fileName;
        this.buckets = new HashSet<>();
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

    public ToDoItem[] getItems() {
        return this.items;
    }

    public Set<Bucket> getBuckets() {
        return buckets;
    }

    public void addBucket(String newBucket) {
        this.buckets.add(new Bucket(newBucket));
    }

    public void setItems(ToDoItem[] items) {
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

    /*public void updateBuckets(){
        java.util.ArrayList<Bucket> copyBucket = new ArrayList<>();
        for (int i = 0; i < ListToDos.length; i++) {

            String element = ListToDos[i].getBucket();
            int help = 0;
            for (int j = 0; j < ListToDos.length; j++) {
                if(Buckets.get(j).getBucket() == element) {
                    help++;
                    break;
                }
            }

            if(help == 0) {
                copyBucket.add(new Bucket(element));
            }

        }

        Buckets = copyBucket;
    }*/


    public void renameBucket (int index, String newBucket) {
        this.buckets.remove(Util.getElementAtIndex(this.buckets, index));
        this.buckets.add(new Bucket(newBucket));
    }

    public void add(ToDoItem toDoItem) {
        if (this.items == null) {
            this.items = new ToDoItem[1];
            this.items[0] = toDoItem;
        } else {
            ToDoItem[] newToDoList = new ToDoItem[this.items.length + 1];
            System.arraycopy(this.items, 0, newToDoList, 0, this.items.length);
            newToDoList[newToDoList.length - 1] = toDoItem;
            this.items = newToDoList;
        }
    }

    public void remove(int index) {
        ToDoItem[] newToDoList = new ToDoItem[this.items.length - 1];
        System.arraycopy(this.items, 0, newToDoList, 0, index);
        System.arraycopy(this.items, index + 1, newToDoList, index, this.items.length - 1);
        this.items = newToDoList;
    }

    public void sortByPriority(String order) {
        if (order.equals("asc")) {
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].getPriority().toInt() > this.items[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        } else if (order.equals("desc")) {
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].getPriority().toInt() < this.items[j + 1].getPriority().toInt()) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleUpBucket(String bucket) {
        for (int i = this.items.length-1; i >= 0; i--) {
            for (int j = this.items.length-1; j > 0; j--) {
                if (this.items[j].getBucket().contains(bucket) && !this.items[j - 1].getBucket().contains(bucket)) {
                    ToDoItem temp = this.items[j];
                    this.items[j] = this.items[j - 1];
                    this.items[j - 1] = temp;
                }
            }
        }
    }
    public void sortByCreatedAt(String order) {
        if (order.equals("asc"))
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length-1; j++) {
                    if (this.items[j].getCreatedAt().compareTo(this.items[j + 1].getCreatedAt()) > 0) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        else if (order.equals("desc")) {
            for (int i = 0; i < this.items.length; i++) {
                for (int j = 0; j < this.items.length - 1; j++) {
                    if (this.items[j].getCreatedAt().compareTo(this.items[j + 1].getCreatedAt()) < 0) {
                        ToDoItem temp = this.items[j];
                        this.items[j] = this.items[j + 1];
                        this.items[j + 1] = temp;
                    }
                }
            }
        }
    }
}
