package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class KeyboardInput {
	
	;

    public KeyboardInput() {
        ;
    }


    
    private int lastPressedKey = -1; // Stores the last pressed key code

  
    public boolean isMovementKeyPressed() {
        int[] keys = {
            Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT,
            Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D, Input.Keys.SPACE
        };

        for (int key : keys) {
            if (Gdx.input.isKeyPressed(key)) {
                lastPressedKey = key; // Store the pressed key
                return true;         // Return true on first detected key press
            }
        }

        lastPressedKey = -1; // Reset if no key is pressed
        return false;
    }


    public int getLastPressedKey() {
        return lastPressedKey;
    }


    public String getLastPressedKeyName() {
        return (lastPressedKey != -1) ? Input.Keys.toString(lastPressedKey) : "None";
    }
    
    public void handleExitInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("Escape pressed, exiting the program.");
            Gdx.app.exit(); // Correctly exits the application
        }


    }
}
