package hwr.oop.handlerTests;
import hwr.oop.Priority;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.build.Plugin;
import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    void canDoHighPriority(){

       Priority priority = Priority.fromInt(2);


       Assertions.assertThat(priority).isEqualTo(Priority.HIGH);

       Assertions.assertThat(priority.toInt()).isEqualTo(2);
    }

    @Test
    void canDoMediumPriority(){

        Priority priority = Priority.fromInt(1);

        Assertions.assertThat(priority).isEqualTo(Priority.MEDIUM);

        Assertions.assertThat(priority.toInt()).isEqualTo(1);
    }

    @Test
    void canDoLowPriority(){

        Priority priority = Priority.fromInt(0);

        Assertions.assertThat(priority).isEqualTo(Priority.LOW);

        Assertions.assertThat(priority.toInt()).isEqualTo(0);
    }
}
