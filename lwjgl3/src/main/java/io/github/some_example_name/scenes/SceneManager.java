package io.github.some_example_name.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import io.github.some_example_name.score.ScoreManager;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Game game;
    private Map<String, Screen> scenes;
    private SceneTransition activeTransition;

    public SceneManager(Game game) {
        this.game = game;
        this.scenes = new HashMap<>();
    }

    // Create and add scenes
    public void createScenes() {
        addScene("StartScreen", new StartScreen(this));
        addScene("MainScreen", new MainScreen(this));
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
        	activeTransition = transition;
            activeTransition.startTransition();
        }
        
        // Make sure game is not null and set the screen
        if (game != null) {
            game.setScreen(scenes.get(sceneName)); // Now this works because scenes are of type Screen
        } else {
            System.out.println("Error: Game instance is null");
        }
    }
    
    // New method to pass score manager into the end screen with scoreManager
    public void switchToEndScreen(boolean isWin, ScoreManager scoreManager) {
        EndScreen endScreen = new EndScreen(this, isWin, scoreManager);
        scenes.put("EndScreen", endScreen);
        game.setScreen(endScreen);
    }

 // Fix: Restart the game by recreating GameScreen
    public void restartGame() {
        System.out.println("Restarting game...");
        
     // Stop and dispose of old GameScreen's music if it exists
        if (scenes.get("MainScreen") instanceof MainScreen) {
            ((MainScreen) scenes.get("MainScreen")).stopMusic();
        }
        
        scenes.remove("MainScreen"); // Remove old GameScreen
        addScene("MainScreen", new MainScreen(this)); // Create a new GameScreen instance
        switchScene("MainScreen", new SceneTransition(1.5f)); // Switch to the new GameScreen
    }
    

    // Dispose all scenes to clean up resources
    public void disposeAllScenes() {
        for (Screen scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }
}
