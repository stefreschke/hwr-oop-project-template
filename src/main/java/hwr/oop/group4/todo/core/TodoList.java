package hwr.oop.group4.todo.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TodoList {

    private final List<Project> projects = new ArrayList<>();
    private final Set<Idea> inTray = new HashSet<>();
    private final Set<Task> loseTasks = new HashSet<>();
    private final List<Project> somedayMaybe = new ArrayList<>();


    public List<Project> getProjects() {
        return projects;
    }

    public Set<Idea> getInTray() {
        return inTray;
    }

    public Set<Task> getLoseTasks() {
        return loseTasks;
    }

    public List<Project> getMaybeList() {
        return somedayMaybe;
    }

    public void addIdea(Idea idea) {
        inTray.add(idea);
    }

    public void removeIdea(Idea idea) {
        inTray.remove(idea);
    }

    public void addLoseTask(Task task) {
        loseTasks.add(task);
    }

    public void removeLoseTask(Task task) {
        loseTasks.remove(task);
    }

    public void addSomedayMaybeProject(Project project) {
        somedayMaybe.add(project);
    }

    public void removeSomedayMaybeProject(Project project) {
        somedayMaybe.remove(project);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

}
