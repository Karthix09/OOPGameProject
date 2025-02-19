package io.github.some_example_name.lwjgl3;

import io.github.some_example_name.entities.Entity;

public class NoCollision{
     
      public boolean checkCollision(Entity e1, Entity e2) {
          return false;
      }
      
     
      public void detectCollision(Entity e1, Entity e2) {
          // No collision handling
      }

      public void dispose() {
    	  
      }
}