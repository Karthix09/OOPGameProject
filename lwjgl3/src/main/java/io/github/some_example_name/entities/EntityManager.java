package io.github.some_example_name.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

//The Entity Manager allows you to store all the entities in a list and make changes to all of them at once 

public class EntityManager {
	
	private List<Entity> entityList; //List to store the entities 
	//Constructor 
	public EntityManager() {
		this.entityList = new ArrayList<>();
	}
	
	
	// Add an entity to the manager
	public void addEntity(Entity entity) {
		entityList.add(entity);
//		Entity newEntity = new Entity();
	}
	
	//Call update() on all entities 
    public void updateEntities() {
        for (Entity entity : entityList) {
//        	entity.movement();
//            entity.update();
        }
    }
    
    // Call draw() on all entities
    public void drawEntities(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) { // Calls the draw method for all entities in their respective classes 
        for (Entity entity : entityList) {
            entity.draw(shapeRenderer, spriteBatch); //Passing in SpriteBatch(textureObjects) and ShapeRenderer(Shapes) to draw everything  
        }
    }
    
    // Method to return all entities
    public List<Entity> getEntities() {
        return entityList;
    }

    
    public void disposeEntities() {
        for (Entity entity : entityList) {
            if (entity instanceof Disposable) {
                ((Disposable) entity).dispose();
            }
        }
    }
	

}
