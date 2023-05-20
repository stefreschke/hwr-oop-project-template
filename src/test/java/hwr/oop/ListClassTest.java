package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListClassTest {

    @Test
    void setListName() {
        List list = new List("wrongName");
        list.setName("rightName");
        String TestName = list.getName();
        assertThat(TestName).isEqualTo("rightName");
    }

    @Test
    void addTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH);
        list.add(item);
        ToDoItem[] itemList = new ToDoItem[1];
        itemList[0] = item;
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void removeTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH);
        list.add(item);
        list.remove(0);
        ToDoItem[] itemList = new ToDoItem[0];
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityAscTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("asc");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item3;
        itemList[1] = item2;
        itemList[2] = item;
        assertThat(list.getItems()).isEqualTo(itemList);
    }
    @Test
    void sortByPriorityDescTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.sortByPriority("desc");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item;
        itemList[1] = item2;
        itemList[2] = item3;
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void bubbleUpBucketTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", false, Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", false, Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", false, Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        list.bubbleUpBucket("Personal");
        ToDoItem[] itemList = new ToDoItem[3];
        itemList[0] = item3;
        itemList[1] = item;
        itemList[2] = item2;
        assertThat(list.getItems()).isEqualTo(itemList);
    }

    @Test
    void sortByCreatedAtAscTest() {
        Program testProgram = new Program();
        List list = testProgram.loadList("sortByCreatedAtTest");
        ToDoItem[] sortedExpected = new ToDoItem[3];
        sortedExpected[2] = list.getItems()[0];
        sortedExpected[1] = list.getItems()[2];
        sortedExpected[0] = list.getItems()[1];
        list.sortByCreatedAt("asc");
        assertThat(list.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void sortByCreatedAtDescTest() {
        Program testProgram = new Program();
        List list = testProgram.loadList("sortByCreatedAtTest");
        ToDoItem[] sortedExpected = new ToDoItem[3];
        sortedExpected[2] = list.getItems()[1];
        sortedExpected[1] = list.getItems()[2];
        sortedExpected[0] = list.getItems()[0];
        list.sortByCreatedAt("desc");
        assertThat(list.getItems()).isEqualTo(sortedExpected);
    }

    @Test
    void getBucketsTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", false, Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", false, Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", false, Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        // list.updateBuckets();
        java.util.List<Bucket> testbuckets = list.getBuckets();
        assertThat(testbuckets.get(0).getBucket()).isEqualTo("Uni");
        assertThat(testbuckets.get(1).getBucket()).isEqualTo("Math");
        assertThat(testbuckets.get(2).getBucket()).isEqualTo("Personal");
    }
    /*@Test
    void updateBucketsTest() {
        List list = new List("myList");
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b.", "Uni", false, Priority.HIGH);
        ToDoItem item2 = new ToDoItem("Calculate Something", "More Math over here", "Math", false, Priority.MEDIUM);
        ToDoItem item3 = new ToDoItem("Be Amazing", "Just Do It", "Personal", false, Priority.LOW);
        list.add(item);
        list.add(item2);
        list.add(item3);
        //list.updateBuckets();
        java.util.List<Bucket> testbuckets = list.getBuckets();
        assertThat(testbuckets.get(0).getBucket()).isEqualTo("Uni");
        assertThat(testbuckets.get(1).getBucket()).isEqualTo("Math");
        assertThat(testbuckets.get(2).getBucket()).isEqualTo("Personal");
    }*/


    @Test
    void addBucketTest() {
        List list =  new List("myList");
        list.addBucket("Test");
        assertThat(list.getBuckets().get(0).getBucket()).isEqualTo("Test");
    }

    @Test
    void editBucketTest() {
        List list = new List("myList");
        list.addBucket("Test");
        list.editBucket(0, "Boo");
        assertThat(list.getBuckets().get(0).getBucket()).isEqualTo("Boo");
    }
}
