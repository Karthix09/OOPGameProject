package io.github.some_example_name.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ImmovableEntity extends Entity{
		//For drawing TextureObjects
		private Texture texture;
		private SpriteBatch batch;

	//  Constructor for IMMovable Texture Object
		ImmovableEntity(String filePath, float x, float y, boolean isCollidable, SpriteBatch batch){
			super(x, y, isCollidable);
			this.texture = new Texture(Gdx.files.internal(filePath));
			this.batch = batch;
		}

		//Update Method
		@Override
		public void update() {}

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
			//Setters
			public void setTexture(Texture tex) {
				this.texture = tex;
			}

			//Drawing the objects
			@Override
			public void draw(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
				batch.draw(this.texture, getPosX(), getPosY(), texture.getWidth(), texture.getHeight());
			}

			@Override
			public boolean hasCollided() {

				return false;
			}

			@Override
			public void setCollided(boolean collided) {


			}


}
