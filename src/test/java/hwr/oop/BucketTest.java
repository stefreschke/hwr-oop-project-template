package hwr.oop;

import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketTest {
    @Test
    void setTitle() {
        Bucket bucket = new Bucket("");
        bucket.setBucketName("testTitle");
        String testName = bucket.getBucketName();
        assertThat(testName).isEqualTo("testTitle");
    }

    @Test
    void getTitle() {
        Bucket bucket = new Bucket("testTitle");
        String testName = bucket.getBucketName();
        assertThat(testName).isEqualTo("testTitle");
    }
    @Test
    void addBucketTest() {
        ToDoList toDoList =  new ToDoList("myList");
        assertThat(toDoList.getBuckets().size()).isEqualTo(0);
        Bucket bucket = new Bucket("Test");
        toDoList.addBucket(bucket);
        assertThat(toDoList.getBuckets()).hasSize(1);
        assertThat(toDoList.getBuckets()).contains(bucket);
    }

    @Test
    void editBucketTest() {
        ToDoList toDoList = new ToDoList("myList");
        Bucket bucket = new Bucket("Test");
        toDoList.addBucket(bucket);
        toDoList.renameBucket(0, "Boo");
        assertThat(Util.getElementAtIndex(toDoList.getBuckets(), 0).getBucketName()).isEqualTo("Boo");
    }
}
