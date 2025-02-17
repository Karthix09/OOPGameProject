package io.github.some_example_name.lwjgl3;

public class MovementManager implements iMovable{
	
	 private float speed;
	 private boolean isPlayer;
	    
	    // Constructor that allows setting isPlayer to true or false
	    public MovementManager(float speed, boolean isPlayer) {
	        this.speed = speed;
	        this.isPlayer = isPlayer;
	    }


	 public float getSpeed() {
	        return speed;
	    }

	    public void setSpeed(float speed) {
	        this.speed = speed;
	    }

	    // Default movement behavior (can be overridden)
	    public void updateAiMovement() {
	        System.out.println("Default movement logic in MovementManager.");
	    }

	    // Method Overloading for different player movement scenarios
	    public void updatePlayerMovement(String direction) {
	        System.out.println("Updating movement in direction: " + direction);
	    }

	    public void updatePlayerMovement(String direction, float speed) {
	        System.out.println("Moving in direction: " + direction + " with speed: " + speed);
	    }

	    @Override
	    public void movement() {
	        System.out.println("Executing movement logic.");
	    }


}
