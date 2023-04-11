package hwr.oop.todo;

public class Task extends TaskData{
    private final String id;

    public Task(TaskData data, String id){
        super(data.getTitle(), data.getDescription());
        this.id = id;
    }

    public static Task fromData(TaskData data){
        // TODO: Generate id
        return new Task(data, "abc");
    }

    public String getId() {
        return id;
    }
}
