package io.github.some_example_name.lwjgl3;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.MovableEntity;

import java.util.List;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class HandleCollision extends CollisionManager {

    private int playerCollisions = 0; // Track collisions
    private boolean hasCollided = false;

// old method with fixed collision size
//    @Override
//    public boolean checkCollision(Entity e1, Entity e2) {
//        Rectangle rect1 = new Rectangle(e1.getPosX(), e1.getPosY(), 32, 32); // Adjust size
//        Rectangle rect2 = new Rectangle(e2.getPosX(), e2.getPosY(), 32, 32);
//        return Intersector.overlaps(rect1, rect2);
//    }
    // new method with scalable collision size
//    @Override
//    public boolean checkCollision(Entity e1, Entity e2) {
//        float width1 = e1 instanceof MovableEntity ? ((MovableEntity) e1).getTexture().getWidth() : 32;
//        float height1 = e1 instanceof MovableEntity ? ((MovableEntity) e1).getTexture().getHeight() : 32;
//        
//        float width2 = e2 instanceof MovableEntity ? ((MovableEntity) e2).getTexture().getWidth() : 32;
//        float height2 = e2 instanceof MovableEntity ? ((MovableEntity) e2).getTexture().getHeight() : 32;
//
//        Rectangle rect1 = new Rectangle(e1.getPosX(), e1.getPosY(), width1, height1);
//        Rectangle rect2 = new Rectangle(e2.getPosX(), e2.getPosY(), width2, height2);
//
//        return Intersector.overlaps(rect1, rect2);
//    }
    @Override
    public boolean checkCollision(Entity e1, Entity e2) {
        int playerHitboxWidth = 30;  // Adjust for the player size
        int playerHitboxHeight = 50; // Adjust for player height

        int rockHitboxWidth = 20;  // Adjust for the rock size
        int rockHitboxHeight = 20; // Adjust for rock height

        Rectangle rect1 = new Rectangle(e1.getPosX(), e1.getPosY(), 
                                       e1 instanceof MovableEntity ? playerHitboxWidth : rockHitboxWidth,
                                       e1 instanceof MovableEntity ? playerHitboxHeight : rockHitboxHeight);

        Rectangle rect2 = new Rectangle(e2.getPosX(), e2.getPosY(), 
                                       e2 instanceof MovableEntity ? playerHitboxWidth : rockHitboxWidth,
                                       e2 instanceof MovableEntity ? playerHitboxHeight : rockHitboxHeight);

        return Intersector.overlaps(rect1, rect2);
    }



    @Override
    public void detectCollision(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) { // ✅ Avoid duplicate checks
                Entity e1 = entities.get(i);
                Entity e2 = entities.get(j);

                if (e1.isCollidable() && e2.isCollidable()) {
                    if (checkCollision(e1, e2)) {

                        // ✅ Ensure we count only the first collision
                        if (!e1.hasCollided() && !e2.hasCollided()) {
                            
                            System.out.println("Collision detected between " + e1 + " and " + e2);
                            playerCollisions++;
                            System.out.println("Player has " + playerCollisions + " collisions.");

                            // ✅ Mark entities to prevent duplicate collisions
                            e1.setCollided(true);
                            e2.setCollided(true);

                            if (playerCollisions >= 3) {
                                System.out.println("Game Over!");
                            }
                        }
                    }
                }
            }
        }
    }


    public boolean hasCollided() {
        return hasCollided;
    }

    public void setCollided(boolean collided) {
        this.hasCollided = collided;
    }
}