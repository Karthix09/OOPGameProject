package io.github.some_example_name.entities;

import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import io.github.some_example_name.interfaces.iMovable;


public abstract class MovableEntity extends Entity implements iMovable {

	//For drawing TextureObjects
	private Texture texture;
	private SpriteBatch batch;
	private Sprite sprite;
//	private boolean facingRight = true;
	private boolean isAIControlled; //Check if object is AI Controlled


//  Constructor for Movable Texture Object
	MovableEntity(String filePath, float x, float y, float speed, boolean isCollidable, SpriteBatch batch, boolean isAIControlled){
		super(x, y, speed, isCollidable);
		this.texture = new Texture(Gdx.files.internal(filePath));
		this.batch = batch;
		this.isAIControlled = isAIControlled;
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
		public abstract void movement();


		//Basic Movement Methods For Movable Entities (PolyMorphism)
		public abstract void moveLeft();
		public abstract void moveRight();
		public abstract void jump();


		public abstract void setPosition(float clampedX, float clampedY);

}
