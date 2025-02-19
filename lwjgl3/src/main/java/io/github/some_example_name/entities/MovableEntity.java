package io.github.some_example_name.entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.some_example_name.interfaces.iMovable;

public class MovableEntity extends Entity implements iMovable{
	
	//For drawing TextureObjects
	private Texture texture;
	private SpriteBatch batch;
	private boolean hasCollided = false;
	
	private boolean isAIControlled; //Check if object is AI Controlled
	
	
//  Constructor for Movable Texture Object  
	MovableEntity(String filePath, float x, float y, float speed, boolean isCollidable, SpriteBatch batch, boolean isAIControlled){
		super(x, y, speed, isCollidable);
		this.texture = new Texture(Gdx.files.internal(filePath));
		this.batch = batch;
		this.isAIControlled = isAIControlled;
	}
	

	//Update Method
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	

	
		//Setters
		public void setTexture(Texture tex) {
			this.texture = tex;
		}
		public void setAIControlled(boolean isAIControlled) {
			this.isAIControlled = isAIControlled;
		}
		
		
		//Drawing the objects 
		@Override
		public void draw(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
			batch.draw(this.texture, getPosX(), getPosY(), texture.getWidth(), texture.getHeight());
		}
		
		@Override
	    public boolean hasCollided() {
	        return hasCollided;
	    }
	    @Override
	    public void setCollided(boolean collided) {
	        this.hasCollided = collided;
	    }
		
		//Getters 
		public Texture getTexture() {
			return this.texture;
		}
		//Get Texture Width 
		public float getWidth() {
	    	return this.texture.getWidth();
	    }
	    //Get Texture Height
	    public float getHeight() {
	    	return this.texture.getHeight();
	    }
	    
		public boolean isAIControlled() {
			return this.isAIControlled;
		}
		
		
		//Movement Logic 
		//Define Method for AI Movement 
		//Define Method for Manual Movement 
		@Override
		public void movement() {
			//Create a class for AI Movement and UserMovement to set gravity and define Usermovement 
			// if movement is AI controlled write logic for 
	    	// Movement for Stones
	    	if(isAIControlled) {
	    		Random random = new Random();
	    		// When stone hits bottom, reset to top and change x position
	    		if(this.getPosY() <= 0) {
	    			// Randomize stone speed with cap at 4
	    			if(getSpeed() <= 5) {
	    				this.setSpeed(getSpeed() + random.nextFloat() * 2);			
	    			}
	    			else {
	    				this.setSpeed(this.getSpeed() - random.nextFloat() * 2);
	    			}
	    						
	    			// Recalculate new randomX for stone
	    			float randomX = random.nextFloat() * (Gdx.graphics.getWidth() - this.getWidth());
	    			this.setPosX(randomX);
	    			this.setPosY(400);
	    		}
	    		// Set stone to keep falling
	    		this.setPosY(this.getPosY() - this.getSpeed());
	    	}
	    	
	    	// Movement for Game Character
	    	else {
	    		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { // Get IOManager
	    			moveLeft();
	    		}
	    		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { //
	    			moveRight();
	    		}
	    		// Clamp the bucket position to the screen boundaries
	    	    this.setPosX(Math.max(0, Math.min(this.getPosX(), Gdx.graphics.getWidth() - this.getWidth())));
	    	}
		}
		public void resetPosition(float newX, float newY) {
		    this.setPosX(newX);
		    this.setPosY(newY);
		    this.setCollided(false); // ✅ Reset collision status
		    System.out.println("Rock respawned at X=" + newX + ", Y=" + newY);
		}


}
