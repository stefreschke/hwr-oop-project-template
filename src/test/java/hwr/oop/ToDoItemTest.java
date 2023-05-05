package hwr.oop;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoItemTest {
    @Test
    void getLocalDateTest() {
        String result = ToDoItem.getLocalDate();
        System.out.println(result);
        assertThat(result).isEqualTo(LocalDate.now().toString());
    }
    @Test
    void getIdTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        int result = item.getId();
        assertThat(result).isEqualTo(0);
    }
    @Test
    void getTitleTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        String result = item.getTitle();
        assertThat(result).isEqualTo("testTitle");
    }

    @Test
    void getDescriptionTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        String result = item.getDescription();
        assertThat(result).isEqualTo("testDesc"+ "\nCreated " + ToDoItem.getLocalDate());
    }

    @Test
    void getTagTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        String result = item.getTag();
        assertThat(result).isEqualTo("testTag");
    }

    @Test
    void isDoneTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        boolean result = item.isDone();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getPriorityTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.LOW);
    }
    @Test
    void setTitleTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        item.setTitle("Title");
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo("Title");
    }
    @Test
    void setDescriptionTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        item.setDescription("Description");
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo("Description");
    }
    @Test
    void setDoneTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        item.setDone(true);
        boolean testDone = item.isDone();
        assertThat(testDone).isEqualTo(true);
    }
    @Test
    void setPriorityTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        item.setPriority(Priority.HIGH);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(Priority.HIGH);
    }
    @Test
    void setTagTest() {
        ToDoItem item = new ToDoItem(0,"testTitle", "testDesc", "testTag", false, Priority.LOW,  new Project("test"));
        item.setTag("Tag");
        String testTag = item.getTag();
        assertThat(testTag).isEqualTo("Tag");
    }
    @Test
    void toStringTest() {
        ToDoItem item = new ToDoItem(0,"Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH,  new Project("test"));
        String result = item.toString();
        System.out.println(result);
        assertThat(result).isEqualTo("❌ " + item.getTitle() + '\n' +
                item.getDescription() +  '\n' +
                "<" + item.getTag() + ">" + ' ' +
                item.getPriority());
    }
    @Test
    void getCreatedAtTest() {
        ToDoItem item = new ToDoItem(0,"Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", false, Priority.HIGH,  new Project("test"));
        String result = item.getCreatedAt().toString();
        System.out.println(result);
        assertThat(item.getDescription()).contains(result);
    }

    @Test
    void getProjectNameTest() {
        ToDoItem item = new ToDoItem(0,"", "", "", false, Priority.LOW, new Project("test"));
        String result = item.getProjectName();
        assertThat(result).isEqualTo("test");
    }

    @Test
    void setProjectNameTest() {
        ToDoItem item = new ToDoItem(0,"", "", "", false, Priority.LOW, new Project(""));
        String test = "test";
        item.setProjectName(test);
        String result = item.getProjectName();
        assertThat(result).isEqualTo("test");
    }

}
