package hwr.oop;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ToDoListTest {

    @Test
    void setListName() {
        ToDoList toDoList = new ToDoList("wrongName");
        toDoList.setName("rightName");
        String TestName = toDoList.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    void addTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        toDoList.add(item);
        ToDoItem[] itemList = new ToDoItem[1];
        itemList[0] = item;
        assertThat(toDoList.getItems()).isEqualTo(itemList);
    }

    @Test
    void removeTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        toDoList.add(item);
        toDoList.remove(0);
        ToDoItem[] itemList = new ToDoItem[0];
        assertThat(toDoList.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityAscTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni",  Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni",  Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni",  Priority.LOW);
        toDoList.add(item);
        toDoList.add(item2);
        toDoList.add(item3);
        toDoList.sortByPriority("asc");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item3;
        itemList[1] = item2;
        itemList[2] = item;
        assertThat(toDoList.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityDescTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni",  Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni",  Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni",  Priority.LOW);
        toDoList.add(item);
        toDoList.add(item2);
        toDoList.add(item3);
        toDoList.sortByPriority("desc");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item;
        itemList[1] = item2;
        itemList[2] = item3;
        assertThat(toDoList.getItems()).isEqualTo(itemList);
    }

    @Test
    void bubbleUpBucketTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni",  Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math",  Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal",  Priority.LOW);
        toDoList.add(item);
        toDoList.add(item2);
        toDoList.add(item3);
        toDoList.bubbleUpBucket("Personal");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item3;
        itemList[1] = item;
        itemList[2] = item2;
        assertThat(toDoList.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByCreatedAtAscTest() {
        Program testProgram = new Program();
        ToDoList toDoList = testProgram.loadToDoList("sortByCreatedAtTest");
        ToDoItem[] sortedExpected = new ToDoItem[3];
        sortedExpected[2] = toDoList.getItems()[0];
        sortedExpected[1] = toDoList.getItems()[2];
        sortedExpected[0] = toDoList.getItems()[1];
        toDoList.sortByCreatedAt("asc");
        assertThat(toDoList.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByCreatedAtDescTest() {
        Program testProgram = new Program();
        ToDoList toDoList = testProgram.loadToDoList("sortByCreatedAtTest");
        ToDoItem[] sortedExpected = new ToDoItem[3];
        sortedExpected[2] = toDoList.getItems()[1];
        sortedExpected[1] = toDoList.getItems()[2];
        sortedExpected[0] = toDoList.getItems()[0];
        toDoList.sortByCreatedAt("desc");
        assertThat(toDoList.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void getBucketsTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni",  Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math",  Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal",  Priority.LOW);
        toDoList.add(item);
        toDoList.add(item2);
        toDoList.add(item3);
        // toDoList.updateBuckets();
        Set<Bucket> testBuckets = toDoList.getBuckets();
        assertThat(testBuckets.size()).isEqualTo(3);
        assertThat(testBuckets.contains(new Bucket("Uni"))).isTrue();
        assertThat(testBuckets.contains(new Bucket("Math"))).isTrue();
        assertThat(testBuckets.contains(new Bucket("Personal"))).isTrue();
    }
    /*@Test
    void updateBucketsTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni",  Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math",  Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal",  Priority.LOW);
        toDoList.add(item);
        toDoList.add(item2);
        toDoList.add(item3);
        //toDoList.updateBuckets();
        java.util.List<Bucket> testbuckets = toDoList.getBuckets();
        assertThat(testbuckets.get(0).getBucket()).isEqualTo("Uni");
        assertThat(testbuckets.get(1).getBucket()).isEqualTo("Math");
        assertThat(testbuckets.get(2).getBucket()).isEqualTo("Personal");
    }*/


    @Test
    void addBucketTest() {
        ToDoList toDoList =  new ToDoList("myList");
        assertThat(toDoList.getBuckets().size()).isEqualTo(0);
        toDoList.addBucket("Test");
        assertThat(toDoList.getBuckets().size()).isEqualTo(1);
        assertThat(toDoList.getBuckets().contains("Test"));
    }

    @Test
    void editBucketTest() {
        ToDoList toDoList = new ToDoList("myList");
        toDoList.addBucket("Test");
        toDoList.editBucket(0, "Boo");
        assertThat(toDoList.getBuckets().contains("Boo")).isTrue();
    }
}
