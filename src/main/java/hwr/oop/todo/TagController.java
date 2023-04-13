package hwr.oop.todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagController {
    private List<String> tags = new ArrayList<>();
    private static final TagController self = new TagController();


    public TagController() {
        this.tags = new ArrayList<>(Arrays.asList("Important", "Homework"));
    }

    public static TagController get() {
        return self;
    }

    public List<String> getTags() {
        return tags;
    }

    public void createTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }
}
