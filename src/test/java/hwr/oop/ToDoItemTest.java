package hwr.oop;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static hwr.oop.util.ConsoleColors.RED_BOLD;
import static hwr.oop.util.ConsoleColors.RESET;
import static org.assertj.core.api.Assertions.assertThat;

class ToDoItemTest {
    @Test
    void getTitleTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testbucket"), Priority.LOW, LocalDate.now());
        String result = item.getTitle();
        assertThat(result).isEqualTo("testTitle");
    }
    @Test
    void getDescriptionTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        String result = item.getDescription();
        assertThat(result).isEqualTo("testDesc");
    }

    @Test
    void getBucketTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testBucket"), Priority.LOW, LocalDate.now());
        String result = item.getBucket().getBucketName();
        assertThat(result).isEqualTo("testBucket");
    }

    @Test
    void isDoneTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        boolean result = item.isDone();
        assertThat(result).isFalse();
    }
    @Test
    void getDueDateTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now());
        LocalDate result = item.getDueDate();
        assertThat(result).isEqualTo(LocalDate.now());
    }
    @Test
    void setDueDateTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now());
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        item.setDueDate(tomorrow);
        LocalDate testDeadline = item.getDueDate();
        assertThat(testDeadline).isEqualTo(tomorrow);
    }

    @Test
    void getPriorityTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.LOW);
    }

    @Test
    void PriorityHighTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.HIGH, LocalDate.now());
        Priority result = item.getPriority();
        assertThat(result).isEqualTo(Priority.HIGH);
    }

    @Test
    void setTitleTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        item.setTitle("Title");
        String testTitle = item.getTitle();
        assertThat(testTitle).isEqualTo("Title");
    }

    @Test
    void getStateTest() {
        ToDoItem toDoItem = new ToDoItem("t","d",new Bucket("t"), Priority.LOW, LocalDate.now());
        String expectedoutput = "TODO";
        assertThat(toDoItem.getState()).isEqualTo(expectedoutput);
    }

    @Test
    void setDescriptionTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        item.setDescription("Description");
        String testDescription = item.getDescription();
        assertThat(testDescription).isEqualTo("Description");
    }
    @Test
    void setDoneTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        item.setDone();
        boolean testDone = item.isDone();
        assertThat(testDone).isTrue();
    }
    @Test
    void setPriorityTest() {
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        item.setPriority(Priority.HIGH);
        Priority testPriority = item.getPriority();
        assertThat(testPriority).isEqualTo(Priority.HIGH);
    }
    @Test
    void setBucketTest() {
        ToDoList toDoList = new ToDoList("myList");
        ToDoItem item = new ToDoItem("testTitle", "testDesc", new Bucket("testTag"), Priority.LOW, LocalDate.now());
        Bucket possibleExistingBucket = toDoList.findBucket("Tag");
        if (possibleExistingBucket == null) {
            item.setBucket(new Bucket("Tag"));
            toDoList.addBucket(new Bucket("Tag"));
        } else {
            item.setBucket(possibleExistingBucket);
        }
        String testTag = item.getBucket().getBucketName();
        assertThat(testTag).isEqualTo("Tag");
    }
    @Test
    void toStringTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.MEDIUM, LocalDate.now());
        String result = item.toString();
        System.out.println(result);
        String priority = item.getPriority().getColoredString();
        String state = item.getState().getStateEmoji();
        assertThat(result).isEqualTo(state + " " + item.getTitle() + '\n' +
                item.getDescription() +  '\n' +
                "<" +"[1;36mUni[0m" + ">" + ' ' +
                priority + ' ' + item.getDueDate());
    }
    @Test
    void getCreatedAtTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.HIGH, LocalDate.now());
        String result = item.getCreatedAt().toString();
        assertThat(item.getCreatedAt()).isEqualTo(result);
    }
    @Test
    void promotionTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        item.promote(); // TODO -> IN PROGRESS
        assertThat(item.getState().toString()).isEqualTo("IN_PROGRESS");
        item.promote(); // IN PROGRESS -> DONE
        assertThat(item.getState().toString()).isEqualTo("DONE");
    }

    @Test
    void demotionTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        // TODO: Too Much!
        item.promote();
        item.demote();
        assertThat(item.getState()).isEqualTo("TODO");
        item.promote();
        item.promote(); // IN PROGRESS -> DONE
        item.demote(); // DONE -> IN PROGRESS
        assertThat(item.getState()).isEqualTo("IN_PROGRESS");
        item.demote();
        item.demote();
        assertThat(item.getState()).isEqualTo("TODO");
        item.promote();
        item.hold();
        item.hold();
        item.demote();
        item.hold();
        item.promote();
        assertThat(item.getState()).isEqualTo("IN_PROGRESS");
        item.hold();
        item.promote();
        assertThat(item.getState()).isEqualTo("IN_PROGRESS");
        item.setDone();
        item.promote();
        item.hold();
        assertThat(item.getState()).isEqualTo("DONE");
        item.promote(); // TODO -> IN PROGRESS
        item.demote(); // IN PROGRESS -> TODO
        assertThat(item.getState().toString()).isEqualTo("TODO");
        item.promote(); // TODO -> IN PROGRESS
        item.promote(); // IN PROGRESS -> DONE
        item.demote(); // DONE -> IN PROGRESS
        assertThat(item.getState().toString()).isEqualTo("IN_PROGRESS");
    }

    @Test
    void holdTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        item.hold(); // TODO -> HOLD = TODO
        assertThat(item.getState().toString()).isEqualTo("TODO");
        item.promote(); // HOLD -> IN PROGRESS
        item.hold();    // IN PROGRESS -> HOLD
        assertThat(item.getState().toString()).isEqualTo("ON_HOLD");
    }

    @Test
    void getPriorityStringTest() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        String result = item.getPriority().getColoredString();
        assertThat(result).isEqualTo("[1;34mLOW[0m");
    }

    @Test
    void getPriotityStringHIGHTest() {
        ToDoItem item = new ToDoItem("t","t",new Bucket("t"),Priority.HIGH,LocalDate.now());
        String result = item.getPriorityString();
        assertThat(result).isEqualTo(RED_BOLD + "HIGH" + RESET);
    }

    @Test
    void getPriorityIntTest() {
        ToDoItem item = new ToDoItem("t","t",new Bucket("t"),Priority.HIGH,LocalDate.now());
        Integer result = item.getPriority().toInt();
        assertThat(result).isEqualTo(2);
        ToDoItem item2 = new ToDoItem("t","t",new Bucket("t"),Priority.fromInt(2),LocalDate.now());
        ToDoItem item3 = new ToDoItem("t","t",new Bucket("t"),Priority.fromInt(3),LocalDate.now());
        ToDoItem item4 = new ToDoItem("t","t",new Bucket("t"),Priority.fromInt(12),LocalDate.now());
        assertThat(item2.getPriority().toInt()).isEqualTo(1);
        assertThat(item3.getPriority().toInt()).isEqualTo(2);
        assertThat(item4.getPriority().toInt()).isEqualTo(0);
    }

    @Test
    void getStateEmojiTestTODO() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        String result = item.getState().getStateEmoji();
        assertThat(result).isEqualTo("⏭️");
    }

    @Test
    void getStatEmojiTestDONE() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        item.setDone();
        String result = item.getStateEmoji();
        assertThat(result).isEqualTo("✅");
    }

    @Test
    void getStateEmojiTestInPROGRESS() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        item.promote();
        String result = item.getStateEmoji();
        assertThat(result).isEqualTo("🏗️");
    }

    @Test
    void getStateEmojiTestHOLD() {
        ToDoItem item = new ToDoItem("Finish Math homework", "I need to do tasks 5 - 10b. Look up on pages 36 and 42 in Analysis I. ", new Bucket("Uni"), Priority.LOW, LocalDate.now());
        item.promote();
        item.hold();
        String result = item.getStateEmoji();
        assertThat(result).isEqualTo("🕑");
    }

    @Test
    void setCreatedAtTest() {
        ToDoItem item = new ToDoItem("", "", new Bucket(""), Priority.LOW, LocalDate.now());
        item.setCreatedAt(LocalDate.of(2020, 1, 1));
        String result = item.getCreatedAt().toString();
        assertThat(result).isEqualTo(LocalDateTime.of(2020, 1, 1, 1, 1).toString());
    }
}

