package io.github.some_example_name.collision;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.entities.Objects;
import io.github.some_example_name.scenes.SceneManager;
import io.github.some_example_name.scenes.SceneTransition;
import io.github.some_example_name.score.ScoreManager;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class HandleCollision extends CollisionManager {

    private SceneManager sceneManager;
    private Sound collisionSound;
    private ScoreManager scoreManager;

    public HandleCollision(SceneManager sceneManager, ScoreManager scoreManager) {
        this.sceneManager = sceneManager;
        this.scoreManager = scoreManager;
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("hurt.wav")); // Load sound file
    }

    @Override
    public boolean checkCollision(Entity e1, Entity e2) {
        Rectangle rect1 = new Rectangle(e1.getPosX(), e1.getPosY(), e1.getWidth(), e1.getHeight());
        Rectangle rect2 = new Rectangle(e2.getPosX(), e2.getPosY(), e2.getWidth(), e2.getHeight());
        return Intersector.overlaps(rect1, rect2);
    }

    @Override
    public void detectCollision(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity e1 = entities.get(i);
                Entity e2 = entities.get(j);

                if (e1.isCollidable() && e2.isCollidable() && checkCollision(e1, e2)) {
                    // Identify which one is the player
                    Entity player = (e1 instanceof MovableEntity && !((MovableEntity) e1).isAIControlled()) ? e1 :
                                    (e2 instanceof MovableEntity && !((MovableEntity) e2).isAIControlled()) ? e2 : null;

                    Entity object = (player == e1) ? e2 : e1;

                    if (player != null && object instanceof Objects) {
                        if (object.getTexture().toString().toLowerCase().contains("vegetable")) {
                            scoreManager.increaseScore();
                            System.out.println("Ate a vegetable! Score: " + scoreManager.getScore());
                        } else if (object.getTexture().toString().toLowerCase().contains("icecream")) {
                            scoreManager.decreaseScore();
                            System.out.println("Ate ice cream! Score: " + scoreManager.getScore());
                        }

                        // Play sound effect
                        collisionSound.play(1.0f);

                        // Reset object's position
                        object.setPosY(600);

                        // Check for win or lose
                        if (scoreManager.hasWon()) {
                            System.out.println("You Win!");
                            sceneManager.switchToEndScreen(true);
                            return;
                        } else if (scoreManager.hasLost()) {
                            System.out.println("Game Over - You Lost!");
                            sceneManager.switchToEndScreen(false);
                            return;
                        }
                    }

                    e1.setCollided(false);
                    e2.setCollided(false);
                }
            }
        }
    }

    @Override
    public void dispose() {
        if (collisionSound != null) {
            collisionSound.dispose(); // Dispose sound to prevent memory leaks
        }
    }
} 
