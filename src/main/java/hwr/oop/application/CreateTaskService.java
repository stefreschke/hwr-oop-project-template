package hwr.oop.application;

import hwr.oop.persistence.LoadTaskPort;
import hwr.oop.persistence.SaveTaskPort;

import java.time.LocalDateTime;
import java.util.List;

public class CreateTaskService implements CreateTaskUseCase {
    private final LoadTaskPort load;
    private final SaveTaskPort save;

    public CreateTaskService(LoadTaskPort load, SaveTaskPort save){
        this.load = load;
        this.save = save;
    }
    @Override
    public void createTask(int id, String title, String content, TaskState state, List<TaskTag> tagList, User creator, LocalDateTime deadline) {
        List<Task> tmp = load.loadTask();
        Task task = new Task(id, title, content, state, tagList, creator, deadline);
        tmp.add(task);
        save.saveTask(tmp);
    }
}
