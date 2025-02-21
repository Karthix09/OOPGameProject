package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.github.some_example_name.entities.Character;
import io.github.some_example_name.entities.EntityManager;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.entities.Objects;
import io.github.some_example_name.movement.MovementManager;
import io.github.some_example_name.lwjgl3.HandleCollision;
import io.github.some_example_name.lwjgl3.IOManager;
import com.badlogic.gdx.audio.Music;
import io.github.some_example_name.lwjgl3.KeyboardInput;

public class MainScreen extends Scene {
    private static final float WORLD_WIDTH = 1344;
    private static final float WORLD_HEIGHT = 768;

    private Texture background;
    private EntityManager entityManager;
    private Character player;
    private Objects fallingRock;
    private MovementManager movementManager;
    private HandleCollision collisionManager;
    private IOManager ioManager;
    private Music backgroundMusic;
    

    public MainScreen(SceneManager sceneManager) {
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
        
        ioManager = new IOManager(sceneManager);

        // Create Player Entity (Movable by user)
        player = new Character("noBackgrnd.png", 30, 0, 500, 10, 10, true, batch, false);
        entityManager.addEntity(player);

     // Create Falling Rock 
        fallingRock = new Objects("Rock.png", WORLD_WIDTH / 2, 600, 2, 10, true, batch, true);
        entityManager.addEntity(fallingRock);
        
     // Load and Play Background Music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f); // Set volume to 10%
        backgroundMusic.play();
        
        
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
        
     // Detect collisions every frame
        if (collisionManager != null) {
            collisionManager.detectCollision(entityManager.getEntities());
        } else {
            System.err.println("collisionManager is NULL during render!");
        }
        
         
    }
    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
            backgroundMusic = null; // Ensure it doesn't get reused
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        stopMusic(); // Stop and dispose of music properly
    }
}
