package hwr.oop.cards;

import java.time.LocalDate;

public class Card {
    private final String question;
    private final String answer;
    private final LocalDate date;

    public Card(String question, String answer){

        this.question = question;
        this.answer = answer;
        this.date = LocalDate.now();
    }
    public String getQuestion() {
        return question;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAnswer() {
        return answer;
    }
}
