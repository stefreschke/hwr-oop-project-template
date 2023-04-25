package hwr.oop.todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ToDoList {
    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Project> projects = new HashMap<>();

    public Task addTask(TaskData taskData){
        UUID id = UUID.randomUUID();
        Task task =  Task.fromData(id, taskData);

        tasks.put(id, task);

        return task;
    }

    public Task getTask(UUID id){
        Task taskData = tasks.get(id);

        if(taskData == null){
            throw new ToDoListException("Task does not exist");
        }

        return taskData;
    }

    public Project createProject(ProjectData data){
        UUID id = UUID.randomUUID();
        Project project = Project.fromData(id, data);

        projects.put(id, project);

        return project;
    }

    public Project getProject(UUID id){
        Project projectData = projects.get(id);

        if(projectData == null){
            throw new ToDoListException("Project does not exist");
        }

        return projectData;
    }
}
