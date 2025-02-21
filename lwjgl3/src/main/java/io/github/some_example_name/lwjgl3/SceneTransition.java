package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class SceneTransition {
    private float duration;
    private float elapsedTime;
    private boolean transitioningIn;
    private Texture fadeTexture;

    public SceneTransition(float duration) {
        this.duration = duration;
        this.elapsedTime = 0;
        this.transitioningIn = false;
        this.fadeTexture = new Texture(Gdx.files.internal("fade.png")); // Load the fade transition image
    }

    public void startTransition() {
        this.elapsedTime = 0; // Reset the timer for the transition
        this.transitioningIn = true;
    }

    public void update(float delta) {
        if (transitioningIn) {
            elapsedTime += delta;
            if (elapsedTime >= duration) {
                transitioningIn = false;
                elapsedTime = duration;
            }
        }
    }

    public void render(SpriteBatch batch, float width, float height) {
        float alpha = transitioningIn ? (elapsedTime / duration) : (1 - elapsedTime / duration);
        
        batch.begin();
        batch.setColor(1, 1, 1, alpha); // Set alpha transparency
        batch.draw(fadeTexture, 0, 0, width, height); // Draw the texture covering the screen
        batch.setColor(1, 1, 1, 1); // Reset color to avoid affecting other draws
        batch.end();
    }

    public boolean isComplete() {
        return elapsedTime >= duration;
    }

    public void dispose() {
        fadeTexture.dispose(); // Free resources when done
    }
}
