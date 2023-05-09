package hwr.oop.application;

import hwr.oop.input.TaskListException;

import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        if (!tasks.removeIf(t -> t.equals(task))) {
            throw new TaskListException("Task not in TaskList");
        }
    }
}