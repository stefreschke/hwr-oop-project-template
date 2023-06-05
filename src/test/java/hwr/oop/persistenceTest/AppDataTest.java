package hwr.oop.persistenceTest;

import hwr.oop.applicationTest.RandomTestData;
import hwr.oop.persistence.AppData;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class AppDataTest {

    @Test
    void equalsTest() {
        AppData appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        AppData copy = new AppData(appData.getProjectList(), appData.getUserList());
        assertThat(appData).isEqualTo(copy);
        assertThat(copy).isEqualTo(appData);
        assertThat(appData.hashCode()).isEqualTo(copy.hashCode());
    }

    @Test
    void equalsExactlyTest() {
        AppData appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        assertThat(appData).isEqualTo(appData);
    }

    @Test
    void differentProjectList_doesNotEqual() {
        AppData appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        AppData copy = new AppData(RandomTestData.getRandomProjects(), appData.getUserList());
        assertThat(appData).isNotEqualTo(copy);
        assertThat(copy).isNotEqualTo(appData);
    }

    @Test
    void differentClass_doesNotEqual() {
        AppData appData = new AppData(RandomTestData.getRandomProjects(), RandomTestData.getRandomUsers());
        assertThat(appData).isNotEqualTo(new Object());
        assertThat(new Object()).isNotEqualTo(appData);
    }
}
