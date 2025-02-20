package io.github.some_example_name.lwjgl3;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.MovableEntity;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


public class HandleCollision extends CollisionManager {

    private int playerCollisions = 0; // Track collisions
    private static final int MAX_COLLISIONS = 10; // Game over threshold
    private SceneManager sceneManager;
    private Sound collisionSound;

    public HandleCollision(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
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
                    System.out.println("Collision detected between " + e1.getClass().getSimpleName() + " and " + e2.getClass().getSimpleName());
                    playerCollisions++;
                    System.out.println("Total Collisions: " + playerCollisions);
                    
                 // Play hurt sound when collision occurs
                    collisionSound.play(1.0f);
                    
                    // Reset collision flag so future collisions are detected
                    e1.setCollided(false);
                    e2.setCollided(false);

                    // If collision threshold is met, transition to end screen
                    if (playerCollisions >= MAX_COLLISIONS) {
                        System.out.println("Game Over! Switching to End Screen.");
                        sceneManager.switchScene("EndScreen", new SceneTransition(1.5f));
                        return;
                    }
                }
            }
        }
    }
    @Override
    public void dispose() {
        if (collisionSound != null) {
            collisionSound.dispose(); //  Dispose sound to prevent memory leaks
        }
    }
}
