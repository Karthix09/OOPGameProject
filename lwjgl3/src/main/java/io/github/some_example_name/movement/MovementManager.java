package io.github.some_example_name.movement;

import java.util.List;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import io.github.some_example_name.entities.Character;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.EntityManager;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.entities.Objects;
import io.github.some_example_name.inputoutput.IOManager;
import io.github.some_example_name.inputoutput.KeyboardInput;

//Movement manager class should be able to call movement methods and 
// 1. Calls all Movement methods 
// 2. Different movement mechanics 

public class MovementManager{
    private static final float WORLD_WIDTH = 1344;
    private static final float WORLD_HEIGHT = 768;
	
	 	private float maxSpeed;
	    private float acceleration = 300;
	    private float jumpForce;
	    private float MAX_SPEED = 2.0f; //Max Speed of 2
	    private float MIN_SPEED = 0.6f; //Min Speed of 0.6
	    private float RESET_HEIGHT = 700f; //Top of Screen

	    
	    private Random random = new Random();
	    
	    KeyboardInput keyboardInput = new KeyboardInput();
	    
	    
	    
	    //Overloaded Constructor to call from GameMaster for Player Movements 
	    public MovementManager(float maxSpeed, float acceleration, float jumpForce) {
	        this.maxSpeed = maxSpeed;
	        this.acceleration = acceleration;
	        this.jumpForce = jumpForce;
	    }
	    
	    //Default Constructor
	    public MovementManager() {
	    }
	    
	    
	  //Processes the input and calls movement for Objects
	    public void processObjInput(Objects object) 
	    { 
	     if (keyboardInput.isMovementKeyPressed()) //Change to input from input output manager 
	     {
	      if(keyboardInput.getLastPressedKeyName().equals("A")) {
	       object.moveLeft(); 
	      }
	      if (keyboardInput.getLastPressedKeyName().equals("D")) {
	       object.moveRight();
	      }
	      if (keyboardInput.getLastPressedKeyName().equals("Space")){ 
	       object.jump();
	      }
	     }     
	     
	     clampPlayerPositionToScreen(object);
	    }

	       //Processes the input and calls movement for Characters 
	       public void processCharInput(Character c) { 
	           
	           if (keyboardInput.isMovementKeyPressed()) 
	           {
	            if (keyboardInput.getLastPressedKeyName().matches("Left")) {
	             c.moveLeft();
	            }
	            if (keyboardInput.getLastPressedKeyName().matches("Right")) {
	             c.moveRight();
	            }
	            if (keyboardInput.getLastPressedKeyName().matches("Space")) {
	             c.jump();
	            }
	           }
	           clampPlayerPositionToScreen(c);
	       }
	    
	    
	    private void clampPlayerPositionToScreen(MovableEntity entity) {
	        float playerWidth = entity.getWidth();
	        float playerHeight = entity.getHeight();

	        // Get the viewport dimensions instead of screen dimensions
	        float worldWidth = WORLD_WIDTH;  // Defined in GameScreen.java
	        float worldHeight = WORLD_HEIGHT;

	        float clampedX = Math.max(0, Math.min(entity.getPosX(), worldWidth - playerWidth));
	        float clampedY = Math.max(0, Math.min(entity.getPosY(), worldHeight - playerHeight));

	        entity.setPosition(clampedX, clampedY); // Set the player's position within screen boundaries
	    }

	    

		//Handles the movement of objects falling from the sky 
		public void handleFallMovement(MovableEntity entity, float Speed) {

			if(entity.getPosY() <= 0) {
				// Randomize speed with cap at 2
				if(entity.getSpeed() <= MAX_SPEED) {
					entity.setSpeed(entity.getSpeed() + random.nextFloat() * 0.3f);			
				}
				else {
					entity.setSpeed(entity.getSpeed() - random.nextFloat() * 0.3f);
				}
							
				// Recalculate new random X for Object
				// Correctly generate a new random X position across full screen width
		        float randomX = random.nextFloat() * (1344 - entity.getWidth());  // Use WORLD_WIDTH from GameScreen
		        entity.setPosX(randomX);
		        entity.setPosY(RESET_HEIGHT);
				}
		}

	    
	    
	 	// Updates all the movable entities 
	    public void updateMovement(EntityManager entityManager) {
	        List<Entity> entities = entityManager.getEntities(); //call getEntities() to get all entities in array list  

	        for (Entity entity : entities) {
	            if (entity instanceof MovableEntity) { //check if entity is an instance of the MovableEntity class 
	                MovableEntity movable = (MovableEntity) entity;
	                movable.movement(); // call movement method in Movable Entity

	            }
	        }
	    }
	   




}
