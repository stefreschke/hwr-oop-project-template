package hwr.oop;

import org.junit.jupiter.api.Test;

import javax.lang.model.element.Name;

import static org.assertj.core.api.Assertions.assertThat;
public class ListJsonTest {
    @Test
    public void listJsonTest() {
        List assertList = new List();
        assertList.setName("assertName");
        assertList.writeToJSON();
        List list = new List();
    }
}
