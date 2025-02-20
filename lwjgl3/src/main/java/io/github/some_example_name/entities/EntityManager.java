package io.github.some_example_name.entities;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

// The Entity Manager allows you to store all the entities in a list and make changes to all of them at once
public class EntityManager {
    private List<Entity> entityList; // List to store the entities

    // Constructor
    public EntityManager() {
        this.entityList = new ArrayList<>();
    }

    // Add an entity to the manager
    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    // Call update() on all entities and apply movement logic
    public void updateEntities() {
        for (Entity entity : entityList) {
            if (entity instanceof MovableEntity) {
                ((MovableEntity) entity).movement(); // Call movement logic inside MovableEntity
            }
            entity.update(); // Ensure all entities update properly
        }
    }

    // Call draw() on all entities
    public void drawEntities(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        for (Entity entity : entityList) {
            entity.draw(shapeRenderer, batch);
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
