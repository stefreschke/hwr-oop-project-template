package hwr.oop.application;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class ChangeTitleService implements ChangeTitleUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangeTitleService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }


    public void changeTitle(Project project, String newTitle){
        int ind =loadPort.loadData().getProjectList().indexOf(project);
        if(ind>=0){
            AppData appData= loadPort.loadData();
            appData.getProjectList().get(ind).changeTitle(newTitle);
            savePort.saveData(appData);
        }
        else{
            throw new ChangeTitleException("Project not found");
        }
    }
}
