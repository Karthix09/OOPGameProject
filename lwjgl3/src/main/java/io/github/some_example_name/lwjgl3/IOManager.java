package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.InputAdapter;

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
