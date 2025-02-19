package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.lwjgl3.SceneManager;

public class IOManager {
    private SceneManager sceneManager;

    public IOManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    // Handles player movement
    public void handlePlayerInput(MovableEntity player) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        }
    }

    // Handles game start input
    public void handleStartInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            System.out.println("Start button pressed, transitioning to GameScreen.");
            sceneManager.switchScene("GamePlay", null);
        }
    }

    // Handles exiting the game
    public void handleExitInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("Escape pressed, transitioning to EndScreen.");
            sceneManager.switchScene("EndScreen", new SceneTransition(1.5f));
        }

    }
}