package hwr.oop.application;

public class ChangeTitleService implements ChangeTitleInterface{
    private Project project;

    public ChangeTitleService(Project project) {
        this.project = project;
    }

    public void changeTitle(String newTitle){
        project.changeTitle(newTitle);
    }
}
