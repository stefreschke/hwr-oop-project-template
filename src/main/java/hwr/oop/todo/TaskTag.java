package hwr.oop.todo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskTag {
    public String title;
    public TaskTag(String title) {
        this.title = title;
    }
}
