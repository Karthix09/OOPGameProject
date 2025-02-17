package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MovableEntity extends Entity implements iMovable{
	
	//For drawing TextureObjects
	private Texture texture;
	private SpriteBatch batch;
	
	private boolean isAIControlled;//Check if object is AI Controlled
	private float speed;//Declare Speed for Movable Entity 
	
	
//  Constructor for Movable Texture Object  
	MovableEntity(String filePath, float x, float y, boolean isCollidable, SpriteBatch batch, boolean isAIControlled, float speed){
		super(x, y, isCollidable);
		this.texture = new Texture(Gdx.files.internal(filePath));
		this.batch = batch;
		this.isAIControlled = isAIControlled;
		this.speed = speed;
	}
	

	//Update Method
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	//Movement Logic 
	public void movement() {
		// TODO Auto-generated method stub
	}
	
		//Setters
		public void setTexture(Texture tex) {
			this.texture = tex;
		}
		public void setSpeed(float speed) {
			this.speed = speed;
		}
		public void setAIControlled(boolean isAIControlled) {
			this.isAIControlled = isAIControlled;
		}
		
		
		//Drawing the objects 
		@Override
		public void draw(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
			batch.draw(this.texture, getPosX(), getPosY(), texture.getWidth(), texture.getHeight());
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
	    public float getSpeed() {
			return this.speed;
		}
		public boolean isAIControlled() {
			return this.isAIControlled;
		}
		
	
//	 // Entity Movement Methods
//    public void moveLeft() {
//    	this.posX = this.posX - this.dropSpeed * Gdx.graphics.getDeltaTime();
//    }
//
//    public void moveRight() {
//    	this.posX = this.posX + this.dropSpeed * Gdx.graphics.getDeltaTime();
//    }
//
//    public void moveDown() {
//    	this.posY = this.posY - this.dropSpeed * Gdx.graphics.getDeltaTime();
//    }
//
//    public void moveUp() {
//    	this.posY = this.posY + this.dropSpeed * Gdx.graphics.getDeltaTime();
//    }

}
