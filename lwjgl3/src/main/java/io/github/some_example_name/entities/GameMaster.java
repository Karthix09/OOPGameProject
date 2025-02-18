package io.github.some_example_name.entities;

<<<<<<< Updated upstream:lwjgl3/src/main/java/io/github/some_example_name/entities/GameMaster.java
import com.badlogic.gdx.ApplicationAdapter;
=======
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
>>>>>>> Stashed changes:lwjgl3/src/main/java/io/github/some_example_name/lwjgl3/GameMaster.java
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.lwjgl3.CollisionManager;
import io.github.some_example_name.lwjgl3.IOManager;
import io.github.some_example_name.lwjgl3.SceneManager;
import io.github.some_example_name.movement.MovementManager;



//2 Implement the movement for sprite game character 
//3 Implement the movement for stones falling from the sky 


public class GameMaster extends ApplicationAdapter{
	
	
	//Drawing Sprites and shapes 
	private SpriteBatch batch;
	private ShapeRenderer shape;//To draw shapes 
	
	
	
	//Managers
	private EntityManager entityManager;//Entity Management
	private CollisionManager collisionManager;//Collision Management
	private MovementManager movementManager;//Movement Management 
	private SceneManager sceneManager;//Scene Management
	private IOManager ioManager;//Input/Output Management 
	


	
	//Entity Objects
	MovableEntity objects; // objects/stones AI Controlled 
	MovableEntity player; // Player
	
	
	
	
	@Override
	public void create() {
<<<<<<< Updated upstream:lwjgl3/src/main/java/io/github/some_example_name/entities/GameMaster.java
		batch = new SpriteBatch();//To draw Sprites 
		shape = new ShapeRenderer();
		entityManager = new EntityManager();
//		collisionManager = new CollisionManager();
		movementManager = new MovementManager();
		
		createEntityObjects();
		
=======
	    batch = new SpriteBatch();
	    shape = new ShapeRenderer();

	    // Initialize entity manager
	    entityManager = new EntityManager(); 

	    // Ensure other managers are initialized
	    collisionManager = new HandleCollision();
	    movementManager = new MovementManager(30, true);
	    sceneManager = new SceneManager(this);
	    ioManager = new IOManager();
        // Add scenes
        sceneManager.addScene("MainMenu", new Scene(sceneManager, Scene.SceneType.MAIN_MENU));
        sceneManager.addScene("GamePlay", new Scene(sceneManager, Scene.SceneType.GAMEPLAY));
        sceneManager.addScene("EndScreen", new Scene(sceneManager, Scene.SceneType.END_SCREEN));
        // Start with the main menu
        sceneManager.switchScene("MainMenu", null);

	    createEntityObjects(); // Make sure entities are created
>>>>>>> Stashed changes:lwjgl3/src/main/java/io/github/some_example_name/lwjgl3/GameMaster.java
	}
	
	//Initialize the entity Objects 
	public void createEntityObjects() {
		//Player Character
		player = new MovableEntity("noBackgrnd.png", 30, 0, 100, true, batch, false);// FilePath, x, y, isCollidable, Spritebatch, isAIControlled, Speed
		//Stone 
		objects = new MovableEntity("Rock1_1_no_shadow.png", 50, 50, 10, true, batch, true);
		//Add entities to EntityManager 
		entityManager.addEntity(player);
		entityManager.addEntity(objects);
	}
	
//	Render method is responsible for drawing the current state of the game to the screen
		
	@Override
	public void render() {

		
	//render Background
	ScreenUtils.clear(0,0,0.2f,1);
	
<<<<<<< Updated upstream:lwjgl3/src/main/java/io/github/some_example_name/entities/GameMaster.java
	movementManager.updateMovement(entityManager);
=======
>>>>>>> Stashed changes:lwjgl3/src/main/java/io/github/some_example_name/lwjgl3/GameMaster.java
	
	batch.begin();
	entityManager.drawEntities(shape, batch);
	batch.end();
		
	}
	
	public void dispose() {
        sceneManager.disposeAllScenes();
    }
		
	}
	

