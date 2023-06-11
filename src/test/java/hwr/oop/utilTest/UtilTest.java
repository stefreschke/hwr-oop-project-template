package hwr.oop.utilTest;

import hwr.oop.Bucket;
import hwr.oop.ToDoList;
import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class UtilTest {
    @Test
    void getElementAtIndexTest(){
        ToDoList list = new ToDoList(("mylist"));
        list.addBucket(new Bucket("Uni"));
        assertThat(Objects.requireNonNull(Util.getElementAtIndex(list.getBuckets(), 0)).getBucketName()).isEqualTo("Uni");
    }
    @Test
    void getElementAtIndexNullTest(){
        ToDoList list = new ToDoList(("mylist"));
        list.addBucket(new Bucket("Uni"));
        assertThat(Util.getElementAtIndex(list.getBuckets(), 2)).isNull();
    }
}
