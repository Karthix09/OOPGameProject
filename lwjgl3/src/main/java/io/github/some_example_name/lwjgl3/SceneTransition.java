package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SceneTransition {
    private float duration;
    private Texture fadeTexture;
    private OrthographicCamera camera;
    private Viewport viewport;

    private static final float WORLD_WIDTH = 1280;
    private static final float WORLD_HEIGHT = 720;

    public SceneTransition(float duration) {
        this.duration = duration;
        this.fadeTexture = new Texture("fade.png");

        // Setup Camera & Viewport
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.viewport.apply();
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
        camera.update();
    }

    public float getDuration() {
        return duration;
    }

    public void renderTransition(SpriteBatch batch, float elapsedTime) {
        float alpha = Math.min(1.0f, elapsedTime / duration);

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(fadeTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.setColor(Color.WHITE);
        batch.end();
    }

    public void dispose() {
        fadeTexture.dispose();
    }
}