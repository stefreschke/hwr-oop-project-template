package hwr.oop.cards;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class NewCardTests {


    @Test
    void testEquals(){

        Card card1 = new Card("1", "2", 3);
        Card card2 = new Card("1", "2", 3);
        assertThat(card1).isEqualTo(card2);
    }

    @Test
    void testEqualsNot(){

        Card card1 = new Card("1", "2", 3);
        Card card2 = new Card("2", "1", 3);
        assertThat(card1).isNotEqualTo(card2);
    }
    @Test
    public void canCreateCard() {

        Topic topic = new Topic("Spanisch");

        topic.createCard("Is 30 dollars too much for Cyberpunk?", "Yes");

        Card testCard = topic.getCardList().get(0);
        assertThat(testCard).isNotNull();
    }
    @Test
    public void canDeleteCard(){
        Topic topic = new Topic("Spanisch");
        topic.createCard("Tisch", "table");
        assertThat(topic.deleteCard(topic.getCardList().get(0)));
    }
    @Test
    public void cannotDeleteCard(){
        Topic topic = new Topic("Spanisch");
        Card card = new Card("Fischkutter", "fishcutter", 1);
        assertThat(!topic.deleteCard(card));
    }

    @Test
    public void canGetQuestion(){

        Card card = new Card("What was the first item to be sold on Ebay?", "A broken laser pointer", 0);

        String testQuestion = card.getQuestion();
        assertThat(testQuestion).isEqualTo("What was the first item to be sold on Ebay?");
    }
    @Test
    public void canGetAnswer(){

        Card card = new Card("What is the largest living organism on earth?", "The Great Barrier Reef", 0);

        String testQuestion = card.getAnswer();
        assertThat(testQuestion).isEqualTo("The Great Barrier Reef");
    }

    @Test
    public void canGetId(){

        Card card = new Card("Test?", "Ja!", 42);

        int id = card.getId();
        assertThat(id).isEqualTo(42);
    }
    @Test
    public void canGetLastLearned(){
        Card card = new Card("Test?", "Ja!", 42);
        Date date = new Date();
        assertThat(date).isCloseTo(card.getLastLearned(),1000);
    }
    @Test
    public void canEditCard(){
        Lernsession lernsession = Lernsession.createLernsessionWith3Boxes();
        Topic topic = new Topic("Spanisch");
        topic.createCard("Soja", "beste!!!");
        lernsession.loadTopic(topic);
        Card card = lernsession.getRandomCard();
        card.edit("Tofu", "auch Beste");
        assertThat(topic.getCardList().get(0).getQuestion()).isEqualTo("Tofu");
    }
}
