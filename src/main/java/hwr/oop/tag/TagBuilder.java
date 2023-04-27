package hwr.oop.tag;

public class TagBuilder {
    private String title;

    public TagBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Tag build() {
        return new Tag(title);
    }
}