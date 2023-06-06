
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

public class ProjectTest{
@Test
void getTitleTest(){
        Project testProject= new Project(UUID.randomUUID(),null, "Title",null);
        assertThat(testProject.getTitle()).isEqualTo("Title");
        }
@Test
void changeTitleTest(){
        Project testProject=new Project(UUID.randomUUID(),null, "Title",null);
        testProject.changeTitle("newTitle");
        assertThat(testProject.getTitle()).isEqualTo("newTitle");
        }
        }
