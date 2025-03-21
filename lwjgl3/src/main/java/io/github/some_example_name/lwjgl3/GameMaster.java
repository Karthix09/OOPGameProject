package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Game;

import io.github.some_example_name.collision.CollisionManager;
import io.github.some_example_name.entities.EntityManager;
import io.github.some_example_name.inputoutput.IOManager;
import io.github.some_example_name.movement.MovementManager;
import io.github.some_example_name.scenes.SceneManager;
import io.github.some_example_name.score.ScoreManager;

public class GameMaster extends Game {
    private SceneManager sceneManager;
    private EntityManager entityManager;
    private MovementManager movementManager;
    private IOManager ioManager;
    private CollisionManager collisionManager;
    private ScoreManager scoreManager;

    @Override
    public void create() {
        entityManager = new EntityManager();
        movementManager = new MovementManager();
        scoreManager = new ScoreManager();
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
