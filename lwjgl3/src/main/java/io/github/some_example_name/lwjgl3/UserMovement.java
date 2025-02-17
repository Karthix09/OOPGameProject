package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;

public class UserMovement extends MovementManager {
	
//    private boolean isUser;
//    private String movementtype;
    private float posX; // Store movement position
    private float posY;

    public UserMovement(String MovementType) {
        super(30, true); // Example speed and player settings
//        this.isUser = true;
//        this.movementtype = MovementType;
        this.posX = 0; // Initialize position (or set it dynamically)
        this.posY = 0;
    }

    // Set position independently
    public void setPosition(float x, float y) {
        this.posX = x;
        this.posY = y;
    }

    public float getPosX() {
        return this.posX;
    }

    public float getPosY() {
        return this.posY;
    }

    // Entity Movement Methods (Now independent)
    public void moveLeft() {
        this.posX -= 500 * Gdx.graphics.getDeltaTime();
    }

    public void moveRight() {
        this.posX += 500 * Gdx.graphics.getDeltaTime();
    }

    public void moveDown() {
        this.posY -= getSpeed() * Gdx.graphics.getDeltaTime();
    }

    public void moveUp() {
        this.posY += getSpeed() * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void movement() {
        System.out.println("User is controlling movement.");
    }

    // Overloaded methods to handle movement differently
    public void userMove() {
        System.out.println("Moving based on previous input.");
    }

    public void userMove(String movementType) {
        System.out.println("Player is moving " + movementType);

        switch (movementType.toLowerCase()) {
            case "left":
                moveLeft();
                System.out.println("Moving left...");
                break;
            case "right":
                moveRight();
                System.out.println("Moving right...");
                break;
            case "up":
                moveUp();
                System.out.println("Moving up...");
                break;
            case "down":
                moveDown();
                System.out.println("Moving down...");
                break;
            default:
                System.out.println("Invalid movement command: " + movementType);
                break;
        }
    }

    public void applyMovement() {
        System.out.println("Applying user movement.");
    }
}
