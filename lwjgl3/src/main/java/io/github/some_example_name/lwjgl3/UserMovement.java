package io.github.some_example_name.lwjgl3;

public class UserMovement extends MovementManager{
	
	 private boolean isUser;
	    private String movementtype;

	    public UserMovement(float speed, String MovementType) {
	        super(speed);
	        this.isUser = true;
	        this.movementtype= MovementType;
	    }

	 // Entity Movement Methods
	    public void moveleft() {
	     setX((getX() - 500 * Gdx.graphics.getDeltaTime()));
	    }

	    public void moveright() {
	     setX(getX() + 500* Gdx.graphics.getDeltaTime());
	    }

	    public void moveDown() {
	     setY((getY() - getSpeed()* Gdx.graphics.getDeltaTime()));
	    }

	    public void moveUp() {
	     setY((getY() + getSpeed() * Gdx.graphics.getDeltaTime()));
	    }
	    @Override
	    public void updateMovement() {
	        System.out.println("User is controlling movement.");
	    }

	    // Overloaded methods to handle movement differently
	    public void Usermove() {
	        System.out.println("Moving based on previous input.");
	    }

	    public void Usermove(String movementtype) {
	        System.out.println("Player is moving " + movementtype);
	        
	     // Overloaded move method that takes input as a string
	        
	        switch (movementtype.toLowerCase()) {
	            case "left":
	                moveleft();
	                System.out.println("Moving left...");
	                break;
	            case "right":
	                moveright();
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
	                System.out.println("Invalid movement command: " + movementtype);
	                break;
	            }
	        }
	    }

	    public void applyMovement() {
	        System.out.println("Applying user movement.");
	    }


}
