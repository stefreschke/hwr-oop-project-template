package hwr.oop.applicationTest;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.application.User;

import java.time.LocalDateTime;
import java.util.*;

public class RandomTestData {
    static Random rand = new Random();

    public static List<String> taskTitles = List.of("Müll Rausbringen", "Katzen füttern", "Bank überfallen",
            "BWL lernen");

    public static List<String> getRandomTaskTitles() {
        List<String> inbox = new ArrayList<>();
        for (int i=0; i< rand.nextInt(10); i++) {
            inbox.add(taskTitles.get(rand.nextInt(taskTitles.size())));
        }
        return inbox;
    }

    public static LocalDateTime getRandomDeadline() {
        return LocalDateTime.now().plusSeconds(rand.nextInt(Integer.MAX_VALUE));
    }

    public static TaskState getRandomTaskState() {
        return TaskState.values()[rand.nextInt(TaskState.values().length)];
    }

    public static Task getRandomTask() {
        String taskTitle = taskTitles.get(rand.nextInt(taskTitles.size()));
        return new Task(UUID.randomUUID(), taskTitle, taskTitle, getRandomTaskState(), getRandomDeadline());
    }

    public static List<Task> getRandomtaskList() {
        List<Task> taskList = new ArrayList<>();
        for (int i=0; i<rand.nextInt(10); i++) {
            taskList.add(getRandomTask());
        }
        return taskList;
    }

    public static User getRandomUser() {
        return new User(UUID.randomUUID(), "user" + rand.nextInt(1000), getRandomTaskTitles(), getRandomtaskList());
    }

    public static List<User> getRandomUsers() {
        List<User> returnList = new ArrayList<>();
        for (int i=0; i< rand.nextInt(10); i++) {
            returnList.add(getRandomUser());
        }
        return returnList;
    }

    public static Map<User, Boolean> getRandomPermissions() {
        Map<User, Boolean> permissions = new HashMap<>();
        for (int i=0; i< rand.nextInt(10); i++) {
            permissions.put(getRandomUser(), rand.nextBoolean());
        }
        return permissions;
    }

    public static Project getRandomProject() {
        return new Project(UUID.randomUUID(), getRandomtaskList(), "project"+rand.nextInt(1000), getRandomPermissions());
    }

    public static List<Project> getRandomProjects() {
        List<Project> projectList = new ArrayList<>();
        for (int i=0; i<rand.nextInt(10); i++) {
            projectList.add(getRandomProject());
        }
        return projectList;
    }
}
