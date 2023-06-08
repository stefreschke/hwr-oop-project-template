package hwr.oop.cards;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {
    private final int id;
    private String question;
    private String answer;
    private Date lastLearned;

    public Card(@JsonProperty("question") String question, @JsonProperty("answer") String answer, @JsonProperty("id") int id) {

        this.question = question;
        this.answer = answer;
        this.id = id;
        this.lastLearned = new Date();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return Objects.equals(question, other.question) && Objects.equals(answer, other.answer) && Objects.equals(id, other.id) && Objects.equals(lastLearned, other.lastLearned);
    }
    @JsonIgnore
    public void edit(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public int getId() { return id; }

    public Date getLastLearned() {
        return lastLearned;
    }
}
