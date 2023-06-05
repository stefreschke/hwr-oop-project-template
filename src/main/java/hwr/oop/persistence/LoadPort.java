package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LoadPort {
    AppData loadData();

    default Project loadProjectById(UUID projectID) {
        AppData appData = loadData();
        for (Project p : appData.getProjectList()) {
            if (p.getId().equals(projectID)) {
                return p;
            }
        }
        throw new ProjectNotInAppDataException("Project not found");
    }

    default User loadUserbyId(UUID userId) {
        AppData appData = loadData();
        for (User u : appData.getUserList()) {
            if (u.getId().equals(userId)) {
                return u;
            }
        }
        throw new UserNotInAppDataException("User not found");
    }

    default List<Project> loadAllUserProjects(UUID userId) {
        AppData appData = loadData();
        List<Project> projectList = new ArrayList<>();
        for (Project p : appData.getProjectList()) {
            for (Map.Entry<User, Boolean> entry : p.getPermissions().entrySet()) {
                if (entry.getKey().getId().equals(userId) && entry.getValue().equals(Boolean.TRUE)) {
                    projectList.add(p);
                }
            }
        }
        return projectList;
    }

}
