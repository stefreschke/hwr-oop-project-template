package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketTest {
    @Test
    void setTitle() {
        Bucket bucket = new Bucket("");
        bucket.setBucketName("testtitle");
        String Testname = bucket.getBucketName();
        assertThat(Testname).isEqualTo("testtitle");
    }

    @Test
    void getTitle() {
        Bucket bucket = new Bucket("testtitle");
        String Testname = bucket.getBucketName();
        assertThat(Testname).isEqualTo("testtitle");
    }
}
