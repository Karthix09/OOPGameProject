package io.github.some_example_name.lwjgl3;

import io.github.some_example_name.lwjgl3.SceneManager;

public class IOManager {
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;

    public IOManager(SceneManager sceneManager) {
        this.keyboardInput = new KeyboardInput(sceneManager);
        this.mouseInput = new MouseInput();
    }

    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }

    public MouseInput getMouseInput() {
        return mouseInput;
    }
}
