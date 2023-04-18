package hwr.oop.todo;

import java.util.ArrayList;
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

    public void createTag(Tag tag) {
        if(tags.contains(tag)) throw new TagException("Tag already exists!");
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        if(!tags.contains(tag)) throw new TagException("Tag does not exists!");
        this.tags.remove(tag);
    }
}
