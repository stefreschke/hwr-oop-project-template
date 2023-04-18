package hwr.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoItemTest {
    @Test
    void getIdTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        int result = item.getId();
        assertThat(result).isEqualTo(0);
    }
    @Test
    void getTitleTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        String result = item.getTitle();
        assertThat(result).isEqualTo("testTitle");
    }

    @Test
    void getDescriptionTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        String result = item.getDescription();
        assertThat(result).isEqualTo("testDesc"+ "\nCreated " + ToDoItem.getLocalDate());
    }

    @Test
    void getTagTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        String result = item.getTag();
        assertThat(result).isEqualTo("testTag");
    }

    @Test
    void isDoneTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        boolean result = item.isDone();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getPriorityTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.LOW);
    }
    @Test
    void setTitleTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        item.setTitle("Title");
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo("Title");
    }
    @Test
    void setDescriptionTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        item.setDescription("Description");
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo("Description");
    }
    @Test
    void setDoneTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        item.setDone(true);
        boolean testDone = item.isDone();
        assertThat(testDone).isEqualTo(true);
    }
    @Test
    void setPriorityTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW);
        item.setPriority(Priority.HIGH);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(Priority.HIGH);
    }
}


