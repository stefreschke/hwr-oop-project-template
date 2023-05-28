package hwr.oop;

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
        toDoList.addBucket("Test");
        assertThat(toDoList.getBuckets()).hasSize(1);
        assertThat(toDoList.getBuckets().contains("Test"));
    }

    @Test
    void editBucketTest() {
        ToDoList toDoList = new ToDoList("myList");
        toDoList.addBucket("Test");
        toDoList.renameBucket(0, "Boo");
        assertThat(toDoList.getBuckets().contains("Boo")).isTrue();
    }
}
