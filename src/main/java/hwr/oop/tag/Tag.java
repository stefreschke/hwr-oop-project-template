package hwr.oop.tag;

public class Tag {
    private final String title;

    protected Tag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}