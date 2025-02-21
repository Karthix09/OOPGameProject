package io.github.some_example_name.entities;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import io.github.some_example_name.lwjgl3.IOManager;

import io.github.some_example_name.movement.MovementManager;


public class Character extends MovableEntity{
	
	private Texture texture;
  private Sprite sprite;

  private float acceleration;
  private float jumpForce = 20f; // Adjusted jump force

  private Vector2 position;

  private boolean isJumping = false;
  private float gravity = -0.6f; // Gravity pull
  private float verticalSpeed = 0f;
  
  IOManager iomanager;
  MovementManager mm;
  

  
//Overloaded Constructor for Player with Acceleration 
  public Character(String filePath, float x, float y, float speed, float acceleration, float jumpforce, boolean isCollidable, SpriteBatch batch, boolean isAIControlled) {
      super(filePath, x, y, speed, isCollidable, batch, isAIControlled);
      this.acceleration = acceleration;
      this.jumpForce = jumpforce;
  }
	

	// Getter and Setter for acceleration
	public float getAcceleration() {
	    return acceleration;
	}

	public void setAcceleration(float acceleration) {
	    this.acceleration = acceleration;
	}

	// Getter and Setter for jumpForce
	public float getJumpForce() {
	    return jumpForce;
	}

	public void setJumpForce(float jumpForce) {
	    this.jumpForce = jumpForce;
	}
	

	public boolean isGrounded() { //Checks if object is on the ground
		if (this.getPosY() == 0) { //Characters should only be movable while its on the ground
			return true;
		}
		else {
			return false; 
		}
	}
	    
	  
	
	// Player will not be AI Controlled for now 
	    @Override
	    public void movement() {
//	    	
	    	mm = new MovementManager();
	    	mm.processCharInput(this);         // Input will be processed by MovementManager

	   }
	    
	    
	   // Characters will have its own implementations of movements 
	    
	    @Override
	    public void moveLeft() {
	    	this.setPosX((this.getPosX() - (this.getSpeed() * Gdx.graphics.getDeltaTime())));
	    	System.out.println("Left");


	    }
	    
	    @Override
	    public void moveRight() {
	    	this.setPosX((this.getPosX() + (this.getSpeed() * Gdx.graphics.getDeltaTime())));
	    	System.out.println("Right");

	    }
	    
	    @Override
	    public void jump() {
	        if (!isJumping) { 
	            isJumping = true;
	            verticalSpeed = jumpForce; 
	            System.out.println("Jumping!");
	        }
	    }
	   
	    public void onCollision() {
	    	
	    }

	    @Override
	    public void update() {
	        if (isJumping) {
	            this.setPosY(getPosY() + verticalSpeed);  
	            verticalSpeed += gravity;  
	            
	            if (getPosY() <= 0) {  
	                setPosY(0);  
	                isJumping = false;
	                verticalSpeed = 0;
	                System.out.println("Landed!");
	            }
	        }
	    }
		@Override
		public void setPosition(float clampedX, float clampedY) {

			this.setPosX(clampedX);
			this.setPosY(clampedY);

		}


		@Override
		public boolean hasCollided() {
			
			return false;
		}


		@Override
		public void setCollided(boolean collided) {
			
			
		}
		
	    
}
