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

    // Create and add scenes
    public void createScenes() {
        addScene("StartScreen", new StartScreen(this));
        addScene("GamePlay", new GameScreen(this));
        addScene("EndScreen", new EndScreen(this));
        
        // Initially show the Start Screen
        switchScene("StartScreen", null);
    }

    // Add a new scene to the map
    public void addScene(String sceneName, Screen scene) {
        if (!scenes.containsKey(sceneName)) {
            scenes.put(sceneName, scene);
        }
    }

    // Switch between scenes with optional transition
    public void switchScene(String sceneName, SceneTransition transition) {
        if (!scenes.containsKey(sceneName)) {
            System.out.println("Error: Scene " + sceneName + " not found.");
            return;
        }

        if (transition != null) {
            // Apply fade transition if specified
            // Transition logic can be handled here if needed
        }
        
        // Make sure game is not null and set the screen
        if (game != null) {
            game.setScreen(scenes.get(sceneName)); // Now this works because scenes are of type Screen
        } else {
            System.out.println("Error: Game instance is null");
        }
    }

    // Dispose all scenes to clean up resources
    public void disposeAllScenes() {
        for (Screen scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }
}
