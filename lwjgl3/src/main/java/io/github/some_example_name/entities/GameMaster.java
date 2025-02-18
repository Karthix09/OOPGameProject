package io.github.some_example_name.entities;

import com.badlogic.gdx.Game;
import io.github.some_example_name.lwjgl3.SceneManager;

public class GameMaster extends Game {
    private SceneManager sceneManager;

    @Override
    public void create() {
        sceneManager = new SceneManager(this);
        sceneManager.createScenes();
        sceneManager.switchScene("StartScreen", null); // Start game at StartScreen
    }

    @Override
    public void render() {
        super.render(); // Ensures the active scene is updated
    }

    @Override
    public void dispose() {
        sceneManager.disposeAllScenes();
    }
}
