package io.github.some_example_name.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity{
	
	private float posX; // Position X of Entity
	private float posY;//Position Y of Entity
	private Color colour; //Just in case we need a color for shapes 
	private boolean isCollidable; //To check if object instantiated is a Collidable.
	private float speed;//Declare Speed for Movable Entity 

	
	//Constructor for IMMovable
	public Entity(float x, float y, boolean isCollidable){
		this.posX = x;
		this.posY = y;
		this.isCollidable = isCollidable;
	}

	//Overloaded Constructor - For Movable
	public Entity(float x, float y, float speed, boolean isCollidable){
			this.posX = x;
			this.posY = y;
			this.speed = speed;
			this.isCollidable = isCollidable;
		}

		//Default Constructor setting values to null
		public Entity(){
			this.posX = 0;
			this.posY = 0;
			this.isCollidable = false;
		}
	
		//Getters 
//		public Color getColor(){
//			return this.colour;
//		}
		public float getPosX() {
			return this.posX;
		}
		public float getPosY() {
			return this.posY;
		}
		public boolean isCollidable() {
			return this.isCollidable;
		}
		public float getSpeed() {
			return this.speed;
		}

	
		//abstract update and draw methods for subclasses
		public abstract void update();
		public abstract void draw(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch);
		
		
		// Entity Movement Methods
	    public void moveLeft() {
	    	this.posX = this.posX - this.speed * Gdx.graphics.getDeltaTime();
	    }

	    public void moveRight() {
	    	this.posX = this.posX + this.speed * Gdx.graphics.getDeltaTime();
	    }

	    public void moveDown() {
	    	this.posY = this.posY - this.speed * Gdx.graphics.getDeltaTime();
	    }

	    public void moveUp() {
	    	this.posY = this.posY + this.speed * Gdx.graphics.getDeltaTime();
	    }
	

		
		//Setters 
//		public void setColor(String colour){
//			if (colour == "green") {
//				this.colour = Color.GREEN;
//				return;
//			}
//			if (colour == "red") {
//				this.colour = Color.RED;
//				return;
//			}
//			else {
//	            this.colour = Color.WHITE; // Default to white if input is null
//	            return;
//	        }
		
		public void setSpeed(float speed) {
			this.speed = speed;
		}
		public void setPosX(float x) {
			this.posX = x;
		}
		public void setPosY(float y) {
			this.posY = y;
		}
		public void setCollidable(boolean isCollidable) {
			this.isCollidable= isCollidable;
		}

		// 🚀 Abstract method for collision tracking
	      public abstract boolean hasCollided();
	      public abstract void setCollided(boolean collided);

}
