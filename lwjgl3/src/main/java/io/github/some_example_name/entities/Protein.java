package io.github.some_example_name.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Protein extends Objects {

    public Protein(String filePath, float x, float y, float speed, float jumpForce, boolean isCollidable, SpriteBatch batch) {
        super(filePath, x, y, speed, jumpForce, isCollidable, batch, true);
    }

    @Override
    public void update() {
        // Additional protein-specific behavior can go here if needed
    }

    @Override
    public boolean hasCollided() {
        return false;
    }

    @Override
    public void setCollided(boolean collided) {
        // Handle collision logic if needed
    }
}
