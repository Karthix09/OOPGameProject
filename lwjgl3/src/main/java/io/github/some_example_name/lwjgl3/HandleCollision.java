package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class HandleCollision extends CollisionManager{
	 @Override
	    public boolean checkCollision(Entity e1, Entity e2) {
	        Rectangle rect1 = new Rectangle(e1.x, e1.y, 1, 1);
	        Rectangle rect2 = new Rectangle(e2.x, e2.y, 1, 1);
	        return Intersector.overlaps(rect1, rect2);
	    }
	    
	    @Override
	    public void detectCollision(Entity e1, Entity e2) {
	        if (checkCollision(e1, e2)) {
	            System.out.println("Collision detected!");
	        }
	    }



}
