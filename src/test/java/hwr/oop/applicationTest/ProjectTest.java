package hwr.oop.applicationTest;

import hwr.oop.application.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
class ProjectTest {
    @Test
    void getTitleTest(){
      Project example = new Project(null,"Title",null);
      String result = example.getTitle();
      assertThat(result).isEqualTo("Title");
    }

}
