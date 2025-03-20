package io.github.some_example_name.collision;

import io.github.some_example_name.entities.Entity;
import java.util.List;

public abstract class CollisionManager {

    public abstract boolean checkCollision(Entity e1, Entity e2);

    public abstract void detectCollision(List<Entity> entities);





    // Force subclasses to implement cleanup logic
    public abstract void dispose();
}
