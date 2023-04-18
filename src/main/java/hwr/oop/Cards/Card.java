package hwr.oop.Cards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer){

        this.question = question;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }

    public String getDate() {
        return "";
    }

    public String getAnswer() {
        return answer;
    }
}
