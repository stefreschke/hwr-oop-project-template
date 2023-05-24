package hwr.oop;

public class Bucket {
    private String bucketName;

    public Bucket(String bucketName) {
        this.bucketName = bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getBucketName() {
        return bucketName;
    }

    public static interface ExitHandler {

    }
}