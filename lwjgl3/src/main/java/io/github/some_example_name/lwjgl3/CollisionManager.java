package io.github.some_example_name.lwjgl3;
import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public abstract class CollisionManager {
	
	protected List<Entity> collidableObjects = new ArrayList<>();

    
    public void addCollidable(Entity e) {
        collidableObjects.add(e);
    }
    
    public void removeCollidable(Entity e) {
        collidableObjects.remove(e);
    }
    
    public abstract boolean checkCollision(Entity e1, Entity e2);
    public abstract void detectCollision(Entity e1, Entity e2);
}
