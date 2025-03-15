package io.github.some_example_name.score;

public class ScoreManager {
    private int score;
    private final int WIN_SCORE = 10;
    private final int LOSE_SCORE = -2;

    public ScoreManager() {
        this.score = 0;
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }

    public int getScore() {
        return score;
    }

    public boolean hasWon() {
        return score >= WIN_SCORE;
    }

    public boolean hasLost() {
        return score <= LOSE_SCORE;
    }

    public void reset() {
        score = 0;
    }
}
