package hwr.oop.todo.library.todolist;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectData;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagException;

import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Project> projects = new HashMap<>();
    private final List<Tag> tags = new ArrayList<>();

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
