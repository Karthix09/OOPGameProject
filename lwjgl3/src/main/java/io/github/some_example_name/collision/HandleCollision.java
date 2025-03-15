package io.github.some_example_name.collision;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.entities.Objects;
import io.github.some_example_name.scenes.MainScreen;
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
    private MainScreen mainScreen;

    public HandleCollision(SceneManager sceneManager, ScoreManager scoreManager, MainScreen mainScreen) {
        this.sceneManager = sceneManager;
        this.scoreManager = scoreManager;
        this.mainScreen = mainScreen;
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("hurt.wav")); // Load sound file
    }
    
 // Checks for rectangular overlap between two entities
    @Override
    public boolean checkCollision(Entity e1, Entity e2) {
        Rectangle rect1 = new Rectangle(e1.getPosX(), e1.getPosY(), e1.getWidth(), e1.getHeight());
        Rectangle rect2 = new Rectangle(e2.getPosX(), e2.getPosY(), e2.getWidth(), e2.getHeight());
        return Intersector.overlaps(rect1, rect2);
    }

 // Detects collisions among all pairs of entities in the list
    @Override
    public void detectCollision(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity e1 = entities.get(i);
                Entity e2 = entities.get(j);
             // Check if both are collidable and are currently colliding
                if (e1.isCollidable() && e2.isCollidable() && checkCollision(e1, e2)) {
                    // Identify which one is the player
                    Entity player = (e1 instanceof MovableEntity && !((MovableEntity) e1).isAIControlled()) ? e1 :
                                    (e2 instanceof MovableEntity && !((MovableEntity) e2).isAIControlled()) ? e2 : null;

                    Entity object = (player == e1) ? e2 : e1;
                 // If valid collision between player and an object
                    if (player != null && object instanceof Objects) {
                    	String textureName = object.getTexture().toString().toLowerCase();
                    	
                    	// Check if the object is a vegetable,protein or ice cream using texture filename
                    	if (textureName.contains("vegetable") || textureName.contains("protein")) {
                            scoreManager.increaseScore();
                            mainScreen.showFloatingText("+1"); // Show green floating +1 text
                            System.out.println("Ate a vegetable! Score: " + scoreManager.getScore());
                        } else if (textureName.contains("icecream")) {
                            scoreManager.decreaseScore();
                            mainScreen.showFloatingText("-1"); // Show red floating -1 text
                            System.out.println("Ate ice cream! Score: " + scoreManager.getScore());
                        }

                        // Play sound effect
                        collisionSound.play(1.0f);

                        // Reset object's position to fall from the top
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
                 // Reset collision flags so they can collide again in future
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
