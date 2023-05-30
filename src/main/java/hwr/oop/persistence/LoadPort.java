package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.User;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LoadPort {
    AppData loadData(Reader fileReader);

    default Project loadProjectById(Reader fileReader, UUID projectID) {
        AppData appData = loadData(fileReader);
        for (Project p : appData.getProjectList()) {
            if (p.getId().equals(projectID)) {
                return p;
            }
        }
        throw new ProjectNotFoundException("Project not found");
    }

    default User loadUserbyId(Reader fileReader, UUID userId) {
        AppData appData = loadData(fileReader);
        for (User u : appData.getUserList()) {
            if (u.getId().equals(userId)) {
                return u;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    default List<Project> loadAllUserProjects(Reader fileReader, UUID userId) {
        AppData appData = loadData(fileReader);
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
