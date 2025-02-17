package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameMaster extends Game {
	
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
	    batch = new SpriteBatch();
	    shape = new ShapeRenderer();

	    // Initialize entity manager
	    entityManager = new EntityManager(); 

	    // Ensure other managers are initialized
	    collisionManager = new HandleCollision();
	    movementManager = new MovementManager(30, true);
	    sceneManager = new SceneManager(this);
	    ioManager = new IOManager();

	    createEntityObjects(); // Make sure entities are created
	}

	//Initialize the entity Objects 
	public void createEntityObjects() {
		//Player Character
		player = new MovableEntity("Run.png", 30, 30, true, batch, false, 30);
		//Stone 
		objects = new MovableEntity("Rock1_1_no_shadow.png", 50, 50, true, batch, true, 20);
		entityManager.addEntity(player);
		entityManager.addEntity(objects);
	}
		
	@Override
	public void render() {
		
	//render Background
	ScreenUtils.clear(0,0,0.2f,1);
	
	batch.begin();
	entityManager.drawEntities(shape, batch);
	batch.end();
		
	}
	
	public void dispose() {
		
	}
	
}
