package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Game game;
    private Map<String, Screen> scenes;

    public SceneManager(Game game) {
        this.game = game;
        this.scenes = new HashMap<>();
    }

    public void createScenes() {
        addScene("StartScreen", new StartScreen(this));
        addScene("GamePlay", new GameScreen(this));
        addScene("EndScreen", new EndScreen(this));

        switchScene("StartScreen", null); // Start at StartScreen
    }

    public void addScene(String sceneName, Screen scene) {
        if (!scenes.containsKey(sceneName)) {
            scenes.put(sceneName, scene);
        }
    }

    public void switchScene(String sceneName, SceneTransition transition) {
        if (!scenes.containsKey(sceneName)) {
            System.out.println("Error: Scene " + sceneName + " not found.");
            return;
        }

        game.setScreen(scenes.get(sceneName));
    }

    public void disposeAllScenes() {
        for (Screen scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }
}
