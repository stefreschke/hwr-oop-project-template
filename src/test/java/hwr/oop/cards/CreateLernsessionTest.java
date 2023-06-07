package hwr.oop.cards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CreateLernsessionTest {
    @Test
    void canCreateLernsessionWith3Boxes(){
        Lernsession lernsession = Lernsession.createLernsessionWith3Boxes();
        int length = lernsession.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(3);
    }@Test
    void canCreateLernsessionWith5Boxes(){
        Lernsession lernsession = Lernsession.createLernsessionWith5Boxes();
        int length = lernsession.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(5);
    }@Test
    void canCreateLernsessionWith7Boxes(){
        Lernsession lernsession = Lernsession.createLernsessionWith7Boxes();
        int length = lernsession.getBoxList().size();
        Assertions.assertThat(length).isEqualTo(7);
    }
    /*@Test
    void canCreateLernsessionFromSave(){
        NewPersistenceLoadPort loadPort = new NewJsonPersistenceAdapter();
        LoadLernsessionFromPersistenceUseCase creator = new LoadLernsessionFromPersistenceUseCase(loadPort);
        Lernsession lernsession = null;
        try{
            lernsession = creator.loadLernsession("test_box");
        }catch (IOException error){
            error.printStackTrace();
        }
        Assertions.assertThat(lernsession.getBoxList()).isNotNull();
    }*/
}