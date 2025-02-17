package io.github.some_example_name.lwjgl3;

public class NoCollision extends CollisionManager{
	   @Override
	    public boolean checkCollision(Entity e1, Entity e2) {
	        return false;
	    }
	    
	    @Override
	    public void detectCollision(Entity e1, Entity e2) {
	        // No collision handling
	    }


}
