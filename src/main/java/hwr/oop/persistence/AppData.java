package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.util.List;

public class AppData {
    private final List<Project> projectList;
    private final List<User> userList;

    public AppData(List<Project> projectList, List<User> userList) {
        this.projectList = projectList;
        this.userList = userList;
    }

}
