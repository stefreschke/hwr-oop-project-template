package hwr.oop;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ListClassTest {

    @ParameterizedTest
    @ValueSource(strings = {"List", "New List"})
    public void setListName(String Name) {
        List list = new List();
        list.setName(Name);
        String TestName = list.Name;
        assertThat(TestName).isEqualTo(Name);
    }

}
