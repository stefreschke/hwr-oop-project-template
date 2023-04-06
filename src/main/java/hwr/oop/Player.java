package hwr.oop;

public class Player {
    int score = 0;
    int currentMultiplier = 0;
    String name = "Default Player Name";

    public Player(String name, int initializedScore) {
        this.name = name;
        score = initializedScore;
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(int initializedScore) {
        score = initializedScore;
    }

    public void scoreAdd(int points) {
        if (points > -1) {
            score += points;
            return;
        }
        throw new IllegalArgumentException("Added Points value must be 0 or higher.");
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }
}


