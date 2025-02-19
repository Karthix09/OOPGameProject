package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.some_example_name.entities.EntityManager;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.movement.MovementManager;
import io.github.some_example_name.lwjgl3.HandleCollision;


public class GameScreen extends Scene {
    private static final float WORLD_WIDTH = 1344;
    private static final float WORLD_HEIGHT = 768;

    private Texture background;
    private EntityManager entityManager;
    private MovableEntity player;
    private MovableEntity fallingRock;
    private MovementManager movementManager;
    private HandleCollision collisionManager;

    public GameScreen(SceneManager sceneManager) {
        super(sceneManager); // Call constructor of the parent class
        background = new Texture("game_bg.png");
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.apply();
        
     // Initialize Managers
        entityManager = new EntityManager();
        movementManager = new MovementManager(); // Ensure movementManager is initialized
        
        if (sceneManager == null) {
            throw new IllegalStateException("SceneManager is NULL! Cannot initialize HandleCollision.");
        }
        
        if (movementManager == null ) {
            throw new IllegalStateException("Managers failed to initialize");
        }
        
        collisionManager = new HandleCollision(sceneManager);
        
        if (collisionManager == null) {
            throw new IllegalStateException("collisionManager failed to initialize");
        }


        // Create Player Entity (Movable by user)
        player = new MovableEntity("noBackgrnd.png", 30, 0, 200, true, batch, false);
        entityManager.addEntity(player);

     // Create Falling Rock (AI-controlled movement, random X position)
        fallingRock = new MovableEntity("Rock.png", WORLD_WIDTH / 2, 600, 100, true, batch, true);
        entityManager.addEntity(fallingRock);
    }
    

    @Override
    public void render(float delta) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();  // Ensure batch begins before drawing
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        entityManager.drawEntities(null, batch);  // Draw entities inside batch
        batch.end();  // Ensure batch ends after drawing

     // Update entity movements
        movementManager.updateMovement(entityManager);
        entityManager.updateEntities();
        
     // Ensure the player does not exceed screen boundaries
        float playerWidth = player.getWidth();
        player.setPosX(Math.max(0, Math.min(player.getPosX(), WORLD_WIDTH - playerWidth)));
        
     // Detect collisions every frame
        if (collisionManager != null) {
            collisionManager.detectCollision(entityManager.getEntities());
        } else {
            System.err.println("collisionManager is NULL during render!");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            sceneManager.switchScene("EndScreen", new SceneTransition(1.5f));  // Switch to the end screen
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }
}
