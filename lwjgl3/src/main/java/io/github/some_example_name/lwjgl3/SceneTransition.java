package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SceneTransition {
    private float duration;
    private float elapsedTime;
    private boolean transitioningIn;
    private ShapeRenderer shapeRenderer;

    public SceneTransition(float duration) {
        this.duration = duration;
        this.elapsedTime = 0;
        this.transitioningIn = false;
        this.shapeRenderer = new ShapeRenderer();
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
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, alpha); // Black color with fade effect
        shapeRenderer.rect(0, 0, width, height);
        shapeRenderer.end();
    }

    public boolean isComplete() {
        return elapsedTime >= duration;
    }
}
