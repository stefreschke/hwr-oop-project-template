package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.util.LocalDateTypeAdapter;
import hwr.oop.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
    public void addBucket(Bucket bucket) {
        buckets.add(bucket);
    }
    public Bucket findBucket(String name) {
        for (Bucket bucket:this.buckets) {
            if (bucket.getBucketName().equals(name)) {
                return bucket;
            }
        }
        return null;
    }
    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }
    public String getFileName() {
        return this.fileName;
    }

    public void writeToJSON(ConsoleUserInterface cui, String fileName) throws FileNotFoundAndCoundNotCreateException {
        pruneUnusedBuckets();
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
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
                throw new FileNotFoundAndCoundNotCreateException("Sorry...File could not be neither found nor created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void pruneUnusedBuckets() {
        int index = 0;
        for (Bucket bucket:this.buckets) {
            int help = 0;
            for (ToDoItem Item : this.items) {
                if (bucket.getBucketName().equals(Item.getBucket().getBucketName())) {
                    help++;
                }
            }
            if (help == 0) {
                this.buckets.remove(Util.getElementAtIndex(buckets, index));
            }
            index++;
        }
    }
    public void renameBucket (int index, String newBucket) {
        try {
            String help = Util.getElementAtIndex(this.buckets, index).getBucketName();
            for (Bucket Bucket : this.buckets) {
                if (Bucket.getBucketName().equals(help)) {
                    Bucket.setBucketName(newBucket);
                }
            }
            //sortBuckets();
            for (ToDoItem ToDoIt : this.items) {
                if (ToDoIt.getBucket().getBucketName().equals(help)) {
                    ToDoIt.getBucket().setBucketName(newBucket);
                }
            }
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Sorry...Bucket could not be found.");
        }
    }
    public void sortBuckets() {
        List<Bucket> bucketList = new ArrayList<>(this.buckets);
        Collections.reverseOrder(Comparator.comparing(Bucket::getBucketName));
        this.buckets = new LinkedHashSet<>(bucketList);
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
    public void sortByDueDate(String order) {
        if (order.equals("asc")) items.sort(Comparator.comparing(ToDoItem::getDueDate));
        else if (order.equals("desc")) items.sort(Comparator.comparing(ToDoItem::getDueDate, Comparator.reverseOrder()));
    }

    public static class FileNotFoundAndCoundNotCreateException extends Exception {
        public FileNotFoundAndCoundNotCreateException(String s) {
            super(s);
        }
    }
}
