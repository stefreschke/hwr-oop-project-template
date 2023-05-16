package hwr.oop.todo.library.todolist;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectData;
import hwr.oop.todo.library.task.Task;

import java.util.HashMap;
import java.util.UUID;

public class ToDoList {
    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Project> projects = new HashMap<>();

    public void addTask(Task task){
        UUID id = task.getId();

        if(tasks.containsKey(id)){
            throw new DuplicateIdException(id);
        }

        tasks.put(id, task);
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
