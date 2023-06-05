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
        try {
            return bucketName;
        } catch (NullPointerException e) {
            return "";
        }
    }
    public String toString() {
        return "🪣" + bucketName;
    }
}