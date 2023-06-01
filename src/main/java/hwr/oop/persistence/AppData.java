package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.util.List;
import java.util.Objects;

public class AppData {
    private final List<Project> projectList;
    private final List<User> userList;

    private final String filePath;

    public AppData(List<Project> projectList, List<User> userList, String filePath) {
        this.projectList = projectList;
        this.userList = userList;
        this.filePath = filePath;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppData appData = (AppData) o;
        return Objects.equals(projectList, appData.projectList) && Objects.equals(userList, appData.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectList, userList);
    }
}
