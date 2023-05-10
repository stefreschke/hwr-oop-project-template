package hwr.oop.application;

import hwr.oop.persistence.LoadTaskPort;
import hwr.oop.persistence.SaveTaskPort;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public void createTask(String title, String content, TaskState state, List<TaskTag> tagList, User creator,
                           LocalDateTime deadline) {
        try {
            List<Task> tmp = load.loadTasks();
            Task task = new Task( title, content, state, tagList, creator, deadline);
            tmp.add(task);
            save.saveTasks(tmp);
        } catch (FileNotFoundException e) {
            //what do we do now?
        } catch (IOException e) {
            //what do we do nIOw?
        }
    }
}
