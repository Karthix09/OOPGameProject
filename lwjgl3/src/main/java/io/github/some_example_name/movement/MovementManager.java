package io.github.some_example_name.movement;

import java.util.List;

import com.badlogic.gdx.utils.Array;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.EntityManager;
import io.github.some_example_name.entities.MovableEntity;

//Movement manager class should be able to call the movement methods on all Movable Entities 

public class MovementManager{
	
	 private float speed;
	 private boolean isPlayer;
	 private boolean isAIControlled;
	    
	    // Constructor that allows setting isPlayer to true or false
//	    public MovementManager(float speed, boolean isPlayer) {
//	        this.speed = speed;
//	        this.isPlayer = isPlayer;
//	    }
	    
	 	// Updates all the movable entities 
	    public void updateMovement(EntityManager entityManager) {
	        List<Entity> entities = entityManager.getEntities(); //call getEntities() to get all entities in array list  

	        for (Entity entity : entities) {
	            if (entity instanceof MovableEntity) { //check if entity is an instance of the MovableEntity class 
	                MovableEntity movable = (MovableEntity) entity;
	                System.out.println(movable.getTexture());//check if texture is correct 
	                movable.movement(); // call movement method in Movable Entity
	            }
	        }
	    }
	    
	    //Update AI Movement 
	    
	    //Update Manual Movement


	 public float getSpeed() {
	        return speed;
	    }

	    public void setSpeed(float speed) {
	        this.speed = speed;
	    }

	    // Default movement behavior (can be overridden)
	    public void updateAiMovement() {
	        System.out.println("Default movement logic in MovementManager.");
	    }

	    // Method Overloading for different player movement scenarios
	    public void updatePlayerMovement(String direction) {
	        System.out.println("Updating movement in direction: " + direction);
	    }
	    
	    public void updatePlayerMovement(String direction, float speed) {
	        System.out.println("Moving in direction: " + direction + " with speed: " + speed);
	    }


}
