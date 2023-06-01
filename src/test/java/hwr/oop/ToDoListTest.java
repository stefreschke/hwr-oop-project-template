package hwr.oop;

import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ToDoListTest {
    @Test
    void setNameTest() {
        ToDoList list = new ToDoList("wrongName");
        list.setName("rightName");
        String TestName = list.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    void getBucketsTest() {
        ToDoList list = new ToDoList("myList");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH));
        list.addBucket(new Bucket("Uni"));
        Set<Bucket> testBuckets = list.getBuckets();
        assertThat(Util.getElementAtIndex(testBuckets, 0).getBucketName()).isEqualTo("Uni");
    }

    @Test
    void addTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        list.add(item);
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void removeTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        list.add(item);
        list.remove(0);
        List<ToDoItem> itemList = new ArrayList<>();
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void addBucketTest() {
        ToDoList list =  new ToDoList("myList");
        list.addBucket(new Bucket("Test"));
        assertThat(Util.getElementAtIndex(list.getBuckets(), 0).getBucketName()).isEqualTo("Test");
    }

    @Test
    void editBucketTest() {
        ToDoList list = new ToDoList("myList");
        list.addBucket(new Bucket("Test"));
        list.renameBucket(0, "Boo");
        assertThat(Util.getElementAtIndex(list.getBuckets(), 0).getBucketName()).isEqualTo("Boo");
    }

    @Test
    void sortByPriorityAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByPriorityDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void bubbleUpBucketTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.bubbleUpBucket("Math");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByCreatedAtAscTest() {
        ToDoList list = new ToDoList("sortByCreatedAtTest");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH));
        list.add(new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM));
        list.add(new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW));
        List<ToDoItem> sortedExpected = new ArrayList<>();
        sortedExpected.add(list.getItems().get(0));
        sortedExpected.add(list.getItems().get(1));
        sortedExpected.add(list.getItems().get(2));
        list.sortByCreatedAt("asc");
        assertThat(list.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByCreatedAtDescTest() {
        ToDoList list = new ToDoList("sortByCreatedAtTest");
        list.add(new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH));
        list.add(new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM));
        list.add(new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW));
        List<ToDoItem> sortedExpected = new ArrayList<>();
        sortedExpected.add(list.getItems().get(2));
        sortedExpected.add(list.getItems().get(1));
        sortedExpected.add(list.getItems().get(0));
        list.sortByCreatedAt("desc");
        assertThat(list.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByTitleAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByTitle("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item3);
        itemList.add(item2);
        itemList.add(item);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByTitleDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByTitle("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByDoneAscTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        item2.setDone();
        list.sortByDone("asc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item2);
        itemList.add(item);
        itemList.add(item3);
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByDoneDescTest() {
        ToDoList list = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Calculate Something", "More Math over here", "Math", Priority.MEDIUM);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", Priority.HIGH);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        item2.setDone();
        list.sortByDone("desc");
        List<ToDoItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item3);
        itemList.add(item2);
        assertThat(list.getItems()).isEqualTo(itemList);
    }
}
