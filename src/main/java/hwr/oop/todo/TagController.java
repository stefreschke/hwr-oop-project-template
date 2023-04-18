package hwr.oop.todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagController {
    private List<Tag> tags = new ArrayList<>();
    private static final TagController self = new TagController();


    public static TagController get() {
        return self;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void createTag(Tag tag) throws TagError {
        if(tags.contains(tag)) throw new TagError("Tag already exists!");
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) throws TagError {
        if(!tags.contains(tag)) throw new TagError("Tag does not exists!");
        this.tags.remove(tag);
    }
}
