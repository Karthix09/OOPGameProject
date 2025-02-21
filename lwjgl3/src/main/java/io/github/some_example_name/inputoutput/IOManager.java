package io.github.some_example_name.inputoutput;

import com.badlogic.gdx.InputAdapter;

import io.github.some_example_name.scenes.SceneManager;

public class IOManager extends InputAdapter {
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;

    public IOManager(SceneManager sceneManager) {
        this.keyboardInput = new KeyboardInput();
        this.mouseInput = new MouseInput(sceneManager);
    }

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }
}
