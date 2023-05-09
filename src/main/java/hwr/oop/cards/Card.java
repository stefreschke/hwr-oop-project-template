package hwr.oop.cards;

public class Card {
    private final int id;
    private final String question;
    private final String answer;

    public Card(String question, String answer, int id){

        this.question = question;
        this.answer = answer;
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() { return id; }
}
