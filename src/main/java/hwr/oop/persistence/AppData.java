package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.util.List;

public class AppData {
    public List<Project> getProjectList() {
        return projectList;
    }

    public List<User> getUserList() {
        return userList;
    }

    private final List<Project> projectList;
    private final List<User> userList;

    public AppData(List<Project> projectList, List<User> userList) {
        this.projectList = projectList;
        this.userList = userList;
    }

}
