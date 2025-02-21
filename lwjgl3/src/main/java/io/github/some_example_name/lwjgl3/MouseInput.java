package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MouseInput {
	private SceneManager sceneManager;

    public MouseInput(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void isLeftClicked() {
    	// Check if the left mouse button was just pressed
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            System.out.println("Start button pressed, transitioning to GameScreen.");
            // Transition to the GamePlay screen
            SceneTransition fadeTransition = new SceneTransition(5f);
            sceneManager.switchScene("MainScreen", fadeTransition);
        }
    }

    public boolean isRightClicked() {
        return Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT);
    }

    public int getMouseX() {
        return Gdx.input.getX();
    }

    public int getMouseY() {
        return Gdx.input.getY();
    }
}
