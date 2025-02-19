package io.github.some_example_name.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

import io.github.some_example_name.lwjgl3.CollisionManager;
import io.github.some_example_name.lwjgl3.HandleCollision;
import io.github.some_example_name.lwjgl3.IOManager;
import io.github.some_example_name.lwjgl3.SceneManager;
import io.github.some_example_name.movement.MovementManager;

public class GameMaster extends Game {
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private SceneManager sceneManager;
    
    // Managers
    private EntityManager entityManager;
    private HandleCollision collisionManager; // Use specific subclass
    private MovementManager movementManager;
    private IOManager ioManager;
    
    // Entity Objects
    private MovableEntity player;
    private MovableEntity objects;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        sceneManager = new SceneManager(this);
        sceneManager.createScenes();
        sceneManager.switchScene("StartScreen", null);
        
        // Initialize managers
        entityManager = new EntityManager();
        collisionManager = new HandleCollision(); // Explicit subclass reference
        movementManager = new MovementManager(); // Ensure this constructor exists
        ioManager = new IOManager();
        
        createEntityObjects();
    }

    private void createEntityObjects() {
        // Initialize player and objects

        player = new MovableEntity("noBackgrnd.png", 100, 50, 100, true, batch, false);
        objects = new MovableEntity("Rock1_1_no_shadow.png", 200, 600, 10, true, batch, true);

        // Add entities to entity manager
        entityManager.addEntity(player);
        entityManager.addEntity(objects);
    }
    
    @Override
    public void render() {
        movementManager.updateMovement(entityManager);
        
        // Check if rocks have fallen past the screen
        for (Entity entity : entityManager.getEntities()) {
            if (entity instanceof MovableEntity) {
                if (entity.getPosY() < 0) { // Rock has fallen off-screen
                    ((MovableEntity) entity).resetPosition(entity.getPosX(), 600); // Reset to top
                }
            }
        }
        
        // check for collision
        collisionManager.detectCollision(entityManager.getEntities());

        batch.begin();
        entityManager.drawEntities(shape, batch);
        batch.end();
        
        super.render();
        sceneManager.update(Gdx.graphics.getDeltaTime());
        sceneManager.render();
    }

    @Override
    public void resize(int width, int height) {
        sceneManager.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        sceneManager.disposeAllScenes();
    }
}
