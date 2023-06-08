package hwr.oop;

import hwr.oop.util.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BucketTest {
    @Test
    void SetBucketNameTest() {
        Bucket bucket = new Bucket("Test");
        bucket.setBucketName("Test1");
        assertThat(bucket.getBucketName()).isEqualTo("Test1");
    }

    @Test
    void GetBucketNameTest() {
        Bucket bucket = new Bucket("Test");
        assertThat(bucket.getBucketName()).isEqualTo("Test");
    }

    @Test
    void ToStringTest() {
        Bucket bucket = new Bucket("Test");
        assertThat(bucket.toString()).hasToString("🪣Test");
    }
}
