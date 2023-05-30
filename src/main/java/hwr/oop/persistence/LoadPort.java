package hwr.oop.persistence;

import hwr.oop.application.Project;
import hwr.oop.application.User;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

public interface LoadPort {
    AppData loadData(Reader fileReader) throws FileNotFoundException;

    Project loadProjectById(Reader fileReader, UUID projectID) throws FileNotFoundException;

    User loadUserbyId(Reader fileReader, UUID userId) throws FileNotFoundException;

    List<Project> loadAllUserProjects(Reader fileReader, UUID userId) throws FileNotFoundException;
    //takes user and gives back all projects this user is involved in
}
