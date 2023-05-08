package hwr.oop;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoItemTest {
    @Test
    void getLocalDateTest() {
        String result = ToDoItem.getLocalDate();
        System.out.println(result);
        assertThat(result).isEqualTo(LocalDate.now().toString());
    }
    @Test
    void getTitleTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        String result = item.getTitle();
        assertThat(result).isEqualTo("testTitle");
    }
    @Test
    void getDescriptionTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        String result = item.getDescription();
        assertThat(result).isEqualTo("testDesc");
    }

    @Test
    void getTagTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        String result = item.getTag();
        assertThat(result).isEqualTo("[1;36mtestTag[0m");
    }

    @Test
    void isDoneTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        boolean result = item.isDone();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getPriorityTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.LOW);
    }
    @Test
    void setTitleTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        item.setTitle("Title");
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo("Title");
    }
    @Test
    void setDescriptionTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        item.setDescription("Description");
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo("Description");
    }
    @Test
    void setDoneTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        item.setDone(true);
        boolean testDone = item.isDone();
        assertThat(testDone).isEqualTo(true);
    }
    @Test
    void setPriorityTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        item.setPriority(Priority.HIGH);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(Priority.HIGH);
    }
    @Test
    void setTagTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", "testTag", Priority.LOW,  new Project("test"));
        item.setTag("Tag");
        String testTag = item.getTag();
        assertThat(testTag).isEqualTo("Tag");
    }
    @Test
    void toStringTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.MEDIUM,  new Project("test"));
        String result = item.toString();
        System.out.println(result);
        String priority = item.getPriorityString();
        String state = item.getStateEmoji();
        assertThat(result).isEqualTo(state + " " + item.getTitle() + '\n' +
                item.getDescription() +  '\n' +
                "<" + item.getTag() + ">" + ' ' +
                priority);
    }
    @Test
    void getCreatedAtTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.HIGH,  new Project("test"));
        String result = item.getCreatedAt();
        assertThat(item.getCreatedAt()).isEqualTo(result);
    }

    @Test
    void getProjectNameTest() {
        ToDoItem item = new ToDoItem("", "", "", Priority.LOW, new Project("test"));
        String result = item.getProjectName();
        assertThat(result).isEqualTo("test");
    }

    @Test
    void setProjectNameTest() {
        ToDoItem item = new ToDoItem("", "", "", Priority.LOW, new Project(""));
        String test = "test";
        item.setProjectName(test);
        String result = item.getProjectName();
        assertThat(result).isEqualTo("test");
    }

    @Test
    void promotionTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW,  new Project("test"));
        item.promote(); // TODO -> IN PROGRESS
        assertThat(item.getState()).isEqualTo("IN_PROGRESS");
        item.promote(); // IN PROGRESS -> DONE
        assertThat(item.getState()).isEqualTo("DONE");
    }

    @Test
    void demotionTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW,  new Project("test"));
        item.promote(); // TODO -> IN PROGRESS
        item.demote(); // IN PROGRESS -> TODO
        assertThat(item.getState()).isEqualTo("TODO");
        item.promote(); // TODO -> IN PROGRESS
        item.promote(); // IN PROGRESS -> DONE
        item.demote(); // DONE -> IN PROGRESS
        assertThat(item.getState()).isEqualTo("IN_PROGRESS");
    }

    @Test
    void holdTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW,  new Project("test"));
        item.hold(); // TODO -> HOLD
        assertThat(item.getState()).isEqualTo("TODO");
        item.promote(); // HOLD -> IN PROGRESS
        item.hold();    // IN PROGRESS -> HOLD
        assertThat(item.getState()).isEqualTo("ON_HOLD");
    }

    @Test
    void getPriorityStringTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW,  new Project("test"));
        String result = item.getPriorityString();
        assertThat(result).isEqualTo("[1;34mLOW[0m");
    }

    @Test
    void getStateEmojiTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", "Uni", Priority.LOW,  new Project("test"));
        String result = item.getStateEmoji();
        assertThat(result).isEqualTo("⏭️");
    }

    @Test
    void setCreatedAtTest() {
        ToDoItem item = new ToDoItem("", "", "", Priority.LOW, new Project(""));
        String test = "test";
        item.setCreatedAt(LocalDateTime.of(2020, 1, 1, 1, 1));
        String result = item.getCreatedAt();
        assertThat(result).isEqualTo(LocalDateTime.of(2020, 1, 1, 1, 1).toString());
    }
}
