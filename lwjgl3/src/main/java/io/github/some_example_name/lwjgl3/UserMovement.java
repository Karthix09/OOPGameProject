package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;

import io.github.some_example_name.entities.Entity;
import io.github.some_example_name.entities.MovableEntity;

public class UserMovement implements iMovable{
	
	 private boolean isUser;
	    private String movementtype;

//	    public UserMovement(float speed, String MovementType) {
//	        super(speed);
////	        this.isUser = true;
////	        this.movementtype= MovementType;
//	    }

	 // Entity Movement Methods
	    public void moveleft(Entity e) {
	     e.setPosX((e.getPosX() - 500 * Gdx.graphics.getDeltaTime()));
	    }

	    public void moveright(Entity e) {
	     e.setPosX(e.getPosX() + 500* Gdx.graphics.getDeltaTime());
	    }
	    
	    public void moveDown(Entity e) {
	     e.setPosY((e.getPosY() - e.getSpeed()* Gdx.graphics.getDeltaTime()));
	    }

	    public void moveUp(Entity e) {
	     e.setPosY((e.getPosY() + e.getSpeed() * Gdx.graphics.getDeltaTime()));
	    }
//	    @Override
//	    public void updateMovement() {
//	        System.out.println("User is controlling movement.");
//	    }

	    // Overloaded methods to handle movement differently
	    public void Usermove() {
	        System.out.println("Moving based on previous input.");
	    }

	    public void Usermove(MovableEntity e, String movementtype) {
	        System.out.println("Player is moving " + movementtype);
	        
	     // Overloaded move method that takes input as a string
//	        
//	        switch (movementtype.toLowerCase()) {
//	            case "left":
//	                moveleft(e);
//	                System.out.println("Moving left...");
//	                break;
//	            case "right":
//	                moveright();
//	                System.out.println("Moving right...");
//	                break;
//	            case "up":
//	                moveUp();
//	                System.out.println("Moving up...");
//	                break;
//	            case "down":
//	                moveDown();
//	                System.out.println("Moving down...");
//	                break;
//	            default:
//	                System.out.println("Invalid movement command: " + movementtype);
//	                break;
//	            }
	        }

	    public void applyMovement() {
	        System.out.println("Applying user movement.");
	    }


		@Override
		public void movement() {
			
		}


}
