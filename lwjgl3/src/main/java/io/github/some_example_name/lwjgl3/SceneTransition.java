package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneTransition {
	 private float duration;
	 private Texture fadeTexture;

	    public SceneTransition(float duration) {
	        this.duration = duration;
	        this.fadeTexture = new Texture("fade.png");
	    }

	    public float getDuration() {
	        return duration;
	    }

	    public void renderTransition(SpriteBatch batch, float elapsedTime) {
	        float alpha = Math.min(1.0f, elapsedTime / duration);
	        batch.begin();
	        batch.setColor(1, 1, 1, alpha);
	        batch.draw(fadeTexture, 0, 0);
	        batch.setColor(Color.WHITE);
	        batch.end();
	    }

	    public void dispose() {
	        fadeTexture.dispose();
	    }


}
