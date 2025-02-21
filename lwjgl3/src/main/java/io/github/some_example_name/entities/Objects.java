package io.github.some_example_name.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.movement.MovementManager;

//Objects will be taken as Rocks as an Example
public class Objects extends MovableEntity {

	private Texture texture;
	private Sprite sprite;
	private MovementManager mm;
	float deltaTime = Gdx.graphics.getDeltaTime();
	
	
	

	public Objects(String filePath, float x, float y, float speed, float jumpforce, boolean isCollidable, SpriteBatch batch, boolean isAIControlled) 
	{
		super(filePath, x, y, speed, isCollidable, batch, isAIControlled);
		this.texture = new Texture(Gdx.files.internal(filePath));
		this.sprite = new Sprite(texture);
	}
	
	@Override
	public void movement() {
		//If AI controlled then AiMovment Handler will handle the falling object 
		
		if(this.isAIControlled() || this.getPosY()>0) //Check if object is AIcontrolled or above ground level 
			
  	{
			this.setPosY(this.getPosY() - this.getSpeed());
  		mm = new MovementManager();
  		mm.handleFallMovement(this, getSpeed());
  	}
		else{
			//Handling Movement for objects 
			mm = new MovementManager();
			mm.processObjInput(this);       //Input will be processed Movement Manager
			
		}

	}
	
	
	
	@Override
	public void moveLeft() {
      setPosX(getPosX() - 200 * deltaTime); // Move left based on speed
      System.out.println("This is being called too");
  }
	
	@Override
	public void moveRight() {
      setPosX(getPosX() + (this.getSpeed() * deltaTime)); // Move right based on speed
  }
	@Override
	public void jump() {
		
	}
	
	@Override
	public void setPosition(float clampedX, float clampedY) {
		// TODO Auto-generated method stub
		this.setPosX(clampedX);
		this.setPosY(clampedY);
	}
	

	@Override
	public void update() {
		
		
	}

	@Override
	public boolean hasCollided() {
		
		return false;
	}

	@Override
	public void setCollided(boolean collided) {
		
		
	}




}
