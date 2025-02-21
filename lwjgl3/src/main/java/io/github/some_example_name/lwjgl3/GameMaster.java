package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Game;

import io.github.some_example_name.scenes.SceneManager;

public class GameMaster extends Game {
    private SceneManager sceneManager;

    @Override
    public void create() {
        // Initialize SceneManager with the current Game instance (GameMaster)
        sceneManager = new SceneManager(this);
        sceneManager.createScenes(); // Create scenes
    }

    @Override
    public void render() {
        super.render(); // Important to call the super render method
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        sceneManager.disposeAllScenes(); // Dispose all scenes when done
        super.dispose();
    }
}
