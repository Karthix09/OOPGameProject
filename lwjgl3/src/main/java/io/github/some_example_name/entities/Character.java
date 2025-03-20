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

import io.github.some_example_name.inputoutput.IOManager;
import io.github.some_example_name.movement.MovementManager;


public class Character extends MovableEntity{

  private float jumpForce; // Adjusted jump force

  private boolean isJumping = false;
  private float gravity; // Gravity pull
  private float verticalSpeed;

  MovementManager mm;



//Overloaded Constructor for Player with Acceleration
  public Character(String filePath, float x, float y, float speed, float jumpforce, boolean isCollidable, SpriteBatch batch, boolean isAIControlled) {
      super(filePath, x, y, speed, isCollidable, batch, isAIControlled);
      this.jumpForce = jumpforce;
  }


	// Getter and Setter for acceleration
	public float getGravity() {
	    return gravity;
	}

	public void setGravity(float gravity) {
	    this.gravity = gravity;
	}

	// Getter and Setter for jumpForce
	public float getJumpForce() {
	    return jumpForce;
	}

	public void setJumpForce(float jumpForce) {
	    this.jumpForce = jumpForce;
	}

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }
    // Getter for verticalSpeed
    public float getVerticalSpeed() {
        return verticalSpeed;
    }

    // Setter for verticalSpeed
    public void setVerticalSpeed(float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

	    // Player will not be AI Controlled for now
	    @Override
	    public void movement() {
	    	mm = new MovementManager();
	    	mm.processCharInput(this); // Input will be processed by MovementManager
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

        //Method is called when spacebar is pressed
	    @Override
	    public void jump() {
	        if (!isJumping()) {
	            this.verticalSpeed = this.jumpForce;
                setJumping(true);
	            System.out.println("Jumping!");
	        }
	    }

        // Runs in Render
	    @Override
	    public void update() {
        //Character gravity will take in (isJumping and verticalSpeed
            this.setPosY(this.getPosY() + this.getVerticalSpeed());
            mm.applyCharGravity(this, this.isJumping());
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
//
		@Override
		public void setCollided(boolean collided) {

		}


}
