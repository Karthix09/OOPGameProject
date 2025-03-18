package io.github.some_example_name.score;
/**
 * ScoreManager handles the game score logic.
 * It tracks the current score, win/lose thresholds,
 * and provides methods to update or reset the score.
 */
public class ScoreManager {
    private int score;
    private final int WIN_SCORE = 10;
    private final int LOSE_SCORE = -2;

    public ScoreManager() {
        this.score = 0;
    }
    
    // Increase score by 1 point
    public void increaseScore() {
        score++;
    }
    
    // Decrease score by 1 point
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
