package io.github.some_example_name.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.CollisionManager;
import io.github.some_example_name.lwjgl3.HandleCollision;
import io.github.some_example_name.lwjgl3.IOManager;
import io.github.some_example_name.lwjgl3.SceneManager;
import io.github.some_example_name.lwjgl3.Scene;
import io.github.some_example_name.movement.MovementManager;

public class GameMaster extends Game {
    private SpriteBatch batch;
    private ShapeRenderer shape;

    // Managers
    private EntityManager entityManager;
    private HandleCollision collisionManager; // Use specific subclass
    private MovementManager movementManager;
    private SceneManager sceneManager;
    private IOManager ioManager;

    // Entity Objects
    private MovableEntity player;
    private MovableEntity objects;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();

        // Initialize managers
        entityManager = new EntityManager();
        collisionManager = new HandleCollision(); // Explicit subclass reference
        movementManager = new MovementManager(); // Ensure this constructor exists
        sceneManager = new SceneManager(this);
        ioManager = new IOManager();

        // Add scenes
        sceneManager.addScene("MainMenu", new Scene(sceneManager, Scene.SceneType.MAIN_MENU));
        sceneManager.addScene("GamePlay", new Scene(sceneManager, Scene.SceneType.GAMEPLAY));
        sceneManager.addScene("EndScreen", new Scene(sceneManager, Scene.SceneType.END_SCREEN));

        // Start with the main menu
        sceneManager.switchScene("GamePlay", null);

        createEntityObjects();
    }

    private void createEntityObjects() {
        // Initialize player and objects
        player = new MovableEntity("noBackgrnd.png", 30, 0, 100, true, batch, false);
        objects = new MovableEntity("Rock1_1_no_shadow.png", 50, 50, 10, true, batch, true);

        // Add entities to entity manager
        entityManager.addEntity(player);
        entityManager.addEntity(objects);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        movementManager.updateMovement(entityManager);

        batch.begin();
        entityManager.drawEntities(shape, batch);
        batch.end();
//        super.render(); // Ensures the current screen is rendered
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        sceneManager.disposeAllScenes();
        entityManager.disposeEntities();
    }
}
