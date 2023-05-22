package hwr.oop;

public class Bucket {
    private String bucketName;

    public Bucket(String bucket) {
        this.bucketName = bucket;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getBucketName() {
        return bucketName;
    }

}