package hwr.oop.group4.todo;

import java.util.*;

public class TodoList {

    private final List<Project> projects = new ArrayList<>();
    private final Set<Idea> inTray = new HashSet<>();
    private final Set<Task> loseTasks = new HashSet<>();
    private final Set<Task> somedayMaybe =  new HashSet<>();


    public List<Project> getProjects() {
        return projects;
    }

    public Set<Idea> getInTray() {
        return inTray;
    }

    public Set<Task> getLoseTasks() {
        return loseTasks;
    }

    public Set<Task> getMaybeList() {
        return somedayMaybe;
    }

    public void addIdea(Idea idea) {
        inTray.add(idea);
    }

    public void addLoseTask(Task task) {
        loseTasks.add(task);
    }

    public void addSomedayMaybeTask(Task task) {
        somedayMaybe.add(task);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

}
