package io.github.some_example_name.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.some_example_name.movement.AIMovement;
import io.github.some_example_name.movement.MovementManager;

//Objects will be taken as Rocks as an Example
public class Objects extends MovableEntity {

	private Texture texture;
	private Sprite sprite;
	private MovementManager mm;
	private float jumpForce;


	public Objects(String filePath, float x, float y, float speed, float jumpforce, boolean isCollidable, SpriteBatch batch, boolean isAIControlled)
	{
		super(filePath, x, y, speed, isCollidable, batch, isAIControlled);
		this.texture = new Texture(Gdx.files.internal(filePath));
		this.sprite = new Sprite(texture);
		this.jumpForce = jumpforce;
	}

	@Override
	public void movement() {
		//The method call to movement manager should be made here and, movement manager will decide whether or not to call manual method or AI Method
        MovementManager mm = new MovementManager();
        mm.processObjInput(this);

	}

	// Getter and Setter for jumpForce
    public float getJumpForce() {
		    return jumpForce;
		}
        public void setJumpForce(float jumpForce) {
		    this.jumpForce = jumpForce;
		}


	@Override
	public void moveLeft() {
      setPosX(this.getPosX() - (this.getSpeed() * Gdx.graphics.getDeltaTime())); // Move left based on speed
      System.out.println("Object moving left");
  }

	@Override
	public void moveRight() {
        setPosX(this.getPosX() + (this.getSpeed() * Gdx.graphics.getDeltaTime())); // Move left based on speed
        System.out.println("Object moving left");
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
//        mm.processObjInput(this);
	}

	@Override
	public boolean hasCollided() {

		return false;
	}

	@Override
	public void setCollided(boolean collided) {
	}




}
