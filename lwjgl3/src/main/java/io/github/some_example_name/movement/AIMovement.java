package io.github.some_example_name.movement;

import io.github.some_example_name.entities.Character;
import io.github.some_example_name.entities.MovableEntity;

import java.util.Random;

public class AIMovement {
    private static final float WORLD_WIDTH = 1344;
    private static final float WORLD_HEIGHT = 768;


    private float gravity = 1;
    private float verticalSpeed;
    private float MAX_SPEED = 5; //Max Speed of 5

    private Random random = new Random();


    //Handles the drop movement for objects
    public void handleDropMechanic(MovableEntity entity, float speed) {

        if(entity.getPosY() <= 0) { // change method to whether is grounded, and handle the movement based on whether the object is grounded
            this.verticalSpeed = speed;
            // Randomize speed with cap at 5
            if(verticalSpeed <= MAX_SPEED) {
                entity.setSpeed(entity.getSpeed() + random.nextFloat() * 0.3f);
            }
            else {
                entity.setSpeed(entity.getSpeed() - random.nextFloat() * 3f);
            }

            // Recalculate new random X for Object
            // Correctly generate a new random X position across full screen width
            float randomX = random.nextFloat() * (WORLD_WIDTH - entity.getWidth());  // Use WORLD_WIDTH from GameScreen
            entity.setPosX(randomX);
            entity.setPosY(WORLD_HEIGHT-entity.getHeight());
        }
    }

    public void gravity(MovableEntity entity) {
        if (entity instanceof Character) {
            Character character = (Character) entity;
            verticalSpeed = character.getVerticalSpeed();
            character.setVerticalSpeed(verticalSpeed -= gravity);
            System.out.println("Jump speed: " + character.getJumpForce());

            if (character.getPosY() <= 0) {
                character.setPosY(0);
                character.setJumping(false);
                character.setVerticalSpeed(0);
                System.out.println("Landed!");
            }
        }

    }
}
