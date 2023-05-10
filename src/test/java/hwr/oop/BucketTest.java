package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketTest {
    @Test
    void setTitle() {
        Bucket bucket = new Bucket("");
        bucket.setBucket("testtitle");
        String Testname = bucket.getBucket();
        assertThat(Testname).isEqualTo("testtitle");
    }

    @Test
    void getTitle() {
        Bucket bucket = new Bucket("testtitle");
        String Testname = bucket.getBucket();
        assertThat(Testname).isEqualTo("testtitle");
    }
}
