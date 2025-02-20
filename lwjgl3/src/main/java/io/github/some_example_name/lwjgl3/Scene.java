package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Scene implements Screen{
    protected SceneManager sceneManager;
    protected SpriteBatch batch;
    protected Viewport viewport;

    // Constructor to initialize common elements
    public Scene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.batch = new SpriteBatch();
    }

    // Common method for rendering, can be overridden by specific scenes
    public abstract void render(float delta);

    // Common resize method for all scenes
    public void resize(int width, int height) {
        if (viewport != null) {
            viewport.update(width, height, true);
        }
    }

    // Dispose common resources
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
    }

    // Common show, pause, resume, hide methods
    public void show() {}
    public void pause() {}
    public void resume() {}
    public void hide() {}
}
