package io.github.some_example_name.scenes;

import java.util.Random;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.github.some_example_name.collision.HandleCollision;
import io.github.some_example_name.entities.Character;
import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.EntityManager;
import io.github.some_example_name.entities.IceCream;
import io.github.some_example_name.entities.MovableEntity;
import io.github.some_example_name.entities.Objects;
import io.github.some_example_name.entities.Vegetable;
import io.github.some_example_name.inputoutput.IOManager;
import io.github.some_example_name.inputoutput.KeyboardInput;
import io.github.some_example_name.movement.MovementManager;
import io.github.some_example_name.score.ScoreManager;

import com.badlogic.gdx.audio.Music;

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
    private ScoreManager scoreManager;

    private BitmapFont font;
    private GlyphLayout layout;
    
    private float spawnTimer;
    private float spawnInterval = 1.5f; // seconds
    private Random random;
    private final float MAX_SPEED = 2.5f;
    
    public MainScreen(SceneManager sceneManager) {
        super(sceneManager); // Call constructor of the parent class
        background = new Texture("game_bg2.jpg");
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.apply();
        
     // Initialize Managers
        entityManager = new EntityManager();
        movementManager = new MovementManager(); // Ensure movementManager is initialized
        scoreManager = new ScoreManager();
        random = new Random();
        
        if (sceneManager == null) {
            throw new IllegalStateException("SceneManager is NULL! Cannot initialize HandleCollision.");
        }
        
        if (movementManager == null ) {
            throw new IllegalStateException("Managers failed to initialize");
        }
        
        if (scoreManager == null) {
            throw new IllegalStateException("ScoreManager failed to initialize");
        }
        
        
        collisionManager = new HandleCollision(sceneManager, scoreManager);
        
        if (collisionManager == null) {
            throw new IllegalStateException("collisionManager failed to initialize");
        }
        
        ioManager = new IOManager(sceneManager);

        // Create Player Entity (Movable by user)
        player = new Character("noBackgrnd.png", 30, 0, 500, 10, 10, true, batch, false);
        entityManager.addEntity(player);

     // Create Falling Rock 
//        fallingRock = new Objects("Rock.png", WORLD_WIDTH / 2, 600, 2, 10, true, batch, true);
//        entityManager.addEntity(fallingRock);
        
     // Load and Play Background Music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.1f); // Set volume to 10%
        backgroundMusic.play();
        
     // Font for score
        font = new BitmapFont();
        font.getData().setScale(2.5f); // Make score text bigger
        layout = new GlyphLayout();
	}

    @Override
    public void render(float delta) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        spawnTimer += delta;
        if (spawnTimer >= spawnInterval) {
            spawnRandomFallingObject();
            spawnTimer = 0;
        }

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();  // Ensure batch begins before drawing
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        entityManager.drawEntities(null, batch);  // Draw entities inside batch

     // Draw score at top-right
        String scoreText = "Score: " + scoreManager.getScore();
        layout.setText(font, scoreText);
        font.draw(batch, scoreText, WORLD_WIDTH - layout.width - 20, WORLD_HEIGHT - 20);
        batch.end();
        
     // Update entity movements and collisions
        movementManager.updateMovement(entityManager);
        entityManager.updateEntities();
        collisionManager.detectCollision(entityManager.getEntities());
        
     // Detect collisions every frame
//        if (collisionManager != null) {
//            collisionManager.detectCollision(entityManager.getEntities());
//        } else {
//            System.err.println("collisionManager is NULL during render!");
//        }
        
         
    }
    
    private void spawnRandomFallingObject() {
        float x = random.nextFloat() * (WORLD_WIDTH - 64); // assuming 64px width max
        float speed = Math.min(1.5f + random.nextFloat(), MAX_SPEED); // cap speed

        boolean spawnVegetable = random.nextBoolean();
        Entity falling;

        if (spawnVegetable) {
            falling = new Vegetable("vegetable.png", x, 600, speed, 0, true, batch);
        } else {
            falling = new IceCream("icecream.png", x, 600, speed, 0, true, batch);
        }

        entityManager.addEntity(falling);
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

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
