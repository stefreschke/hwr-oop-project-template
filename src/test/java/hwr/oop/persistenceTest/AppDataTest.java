package hwr.oop.persistenceTest;

import hwr.oop.applicationTest.RandomTestData;
import hwr.oop.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class AppDataTest {

    String directory = "./OOPTest/";
    AppData appData;
    LoadPort load;
    SavePort save;

    @BeforeEach
    void setUp() {
        appData = new AppData(new ArrayList<>(), new ArrayList<>());
        load = new PersistenceAdapter(directory);
        save = new PersistenceAdapter(directory);

        final File parent = new File(directory);
        parent.mkdirs();
        File file = new File(directory + AppData.class + ".json");
        file.delete();
    }

    @Test
    void equalsTest() {
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        AppData copy = new AppData(appData.getProjectList(), appData.getUserList());
        assertThat(appData).isEqualTo(copy);
        assertThat(copy).isEqualTo(appData);
        assertThat(appData.hashCode()).isEqualTo(copy.hashCode());
    }

    @Test
    void equalsExactlyTest() {
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        assertThat(appData).isEqualTo(appData);
    }

    @Test
    void differentProjectList_doesNotEqual() {
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        AppData copy = new AppData(RandomTestData.getRandomProjects(), appData.getUserList());
        assertThat(appData).isNotEqualTo(copy);
        assertThat(copy).isNotEqualTo(appData);
    }

    @Test
    void differentClass_doesNotEqual() {
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        assertThat(appData).isNotEqualTo(new Object());
        assertThat(new Object()).isNotEqualTo(appData);
    }

    @Test
    void wrongFile_ThrowsLoadException() {
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        save.saveData(appData);
        load = new PersistenceAdapter("./wrongPath");
        try {
            AppData result = load.loadData();
            fail("should throw Exception");
        } catch (CantLoadAppDataException e) {
            e.printStackTrace();
        }
    }

    @Test
    void wrongFile_ThrowsSaveException() {
        appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        save = new PersistenceAdapter("/wrongPath//");
        try {
            save.saveData(appData);
            fail("should throw exception");
        } catch (CantSaveAppDataException e) {
            e.printStackTrace();
        }
    }
}
