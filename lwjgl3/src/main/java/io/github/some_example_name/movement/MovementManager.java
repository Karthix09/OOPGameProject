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


	    KeyboardInput keyboardInput = new KeyboardInput();
        ManualMovement manualMovement = new ManualMovement(); // instantiating object for Manual Movement
        AIMovement aiMovement = new AIMovement();



	    //Overloaded Constructor to call from GameMaster for Player Movements
	    public MovementManager(float maxSpeed, float acceleration, float jumpForce) {
	        this.maxSpeed = maxSpeed;
	        this.acceleration = acceleration;
	        this.jumpForce = jumpForce;
	    }

	    //Default Constructor
	    public MovementManager() {
	    }

      // call object movement for manual movement or AI Movement
	    public void processObjInput(Objects object)
	    {
            if(object.isAIControlled() || object.getPosY()>0) //Check if object is AIcontrolled or above ground level
            {
                  //Calls AI Movement
                  object.setPosY(object.getPosY() - object.getSpeed());
                  aiMovement.handleDropMechanic(object, object.getSpeed());
//                  clampPositionToScreen(object);
            }
            else{
                //Handling ManualMovement for objects - calls process input method
                manualMovement.moveObj(object); // Input will be processed Movement Manager
                clampPositionToScreen(object);
            }
	    }

	       //Processes the input and calls movement for Characters
	       public void processCharInput(Character character) {
               manualMovement.moveChar(character);
	           clampPositionToScreen(character);
	       }
            //Apply Character gravity to ground player when jumping
            public void applyCharGravity(Character character, boolean isJumping) {
                if(isJumping){
                    aiMovement.gravity(character);
                }

            }

	    private void clampPositionToScreen(MovableEntity entity) {
	        float playerWidth = entity.getWidth();
	        float playerHeight = entity.getHeight();

	        // Get the viewport dimensions instead of screen dimensions
	        float worldWidth = WORLD_WIDTH;  // Defined in GameScreen.java
	        float worldHeight = WORLD_HEIGHT;

	        float clampedX = Math.max(0, Math.min(entity.getPosX(), worldWidth - playerWidth));
	        float clampedY = Math.max(0, Math.min(entity.getPosY(), worldHeight - playerHeight));

	        entity.setPosition(clampedX, clampedY); // Set the player's position within screen boundaries
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
