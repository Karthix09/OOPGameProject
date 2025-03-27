package io.github.some_example_name.collision;

import java.util.List;
import io.github.some_example_name.entities.Entity;

public class NoCollision extends CollisionManager {

    @Override
    public boolean checkCollision(Entity e1, Entity e2) {
        return false;
    }

    @Override
    public void detectCollision(List<Entity> entities) {
        // No collision logic, can be implemented for future games
    }

    @Override
    public void dispose() {
        // No resources to clean up
    }
}
