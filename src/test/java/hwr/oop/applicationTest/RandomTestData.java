package hwr.oop.applicationTest;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.application.User;

import java.time.LocalDateTime;
import java.util.*;

class RandomTestData {
    static Random rand = new Random();

    static List<String> taskTitles = List.of("Müll Rausbringen", "Katzen füttern", "Bank überfallen",
            "BWL lernen");

    static List<String> getRandomTaskTitles() {
        List<String> inbox = new ArrayList<>();
        for (int i=0; i< rand.nextInt(10); i++) {
            inbox.add(taskTitles.get(rand.nextInt(taskTitles.size())));
        }
        return inbox;
    }

    static LocalDateTime getRandomDeadline() {
        return LocalDateTime.now().plusSeconds(rand.nextInt(Integer.MAX_VALUE));
    }

    static TaskState getRandomTaskState() {
        return TaskState.values()[rand.nextInt(TaskState.values().length)];
    }

    static List<Task> getRandomtaskList() {
        List<Task> taskList = new ArrayList<>();
        for (int i=0; i<rand.nextInt(10); i++) {
            String task = taskTitles.get(rand.nextInt(taskTitles.size()));
            Task t = new Task(UUID.randomUUID(), task, task, getRandomTaskState(), getRandomDeadline());
            taskList.add(t);
        }
        return taskList;
    }

    static User getRandomUser() {
        return new User(UUID.randomUUID(), "user" + rand.nextInt(1000), getRandomTaskTitles(), getRandomtaskList());
    }

    static List<User> getRandomUsers() {
        List<User> returnList = new ArrayList<>();
        for (int i=0; i< rand.nextInt(10); i++) {
            returnList.add(getRandomUser());
        }
        return returnList;
    }

    static Map<User, Boolean> getRandomPermissions() {
        Map<User, Boolean> permissions = new HashMap<>();
        for (int i=0; i< rand.nextInt(10); i++) {
            permissions.put(getRandomUser(), rand.nextBoolean());
        }
        return permissions;
    }

    static Project getRandomProject() {
        return new Project(UUID.randomUUID(), getRandomtaskList(), "project"+rand.nextInt(1000), getRandomPermissions());
    }

    static List<Project> getRandomProjects() {
        List<Project> projectList = new ArrayList<>();
        for (int i=0; i<rand.nextInt(10); i++) {
            projectList.add(getRandomProject());
        }
        return projectList;
    }
}
