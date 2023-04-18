package hwr.oop.cards;

import java.time.LocalDate;

public class Card {
    private final String question;
    private final String answer;

    public Card(String question, String answer){

        this.question = question;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }

    public LocalDate getDate() {
        return null;
    }

    public String getAnswer() {
        return answer;
    }
}
