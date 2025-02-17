package io.github.some_example_name.lwjgl3;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager {
	private Game game;
    private Map<String, Scene> scenes;
    private SceneTransition currentTransition;
    private float transitionTime = 0;
    private boolean transitioning = false;
    private String nextSceneName;

    public SceneManager(Game game) {
        this.game = game;
        this.scenes = new HashMap<>();
    }

    public void addScene(String sceneName, Scene scene) {
        scenes.put(sceneName, scene);
    }

    public void removeScene(String sceneName) {
        if (scenes.containsKey(sceneName)) {
            if (game.getScreen() == scenes.get(sceneName)) {
                System.out.println("Cannot unload active scene: " + sceneName);
                return;
            }
            scenes.get(sceneName).dispose();
            scenes.remove(sceneName);
        }
    }

    public void switchScene(String sceneName, SceneTransition transition) {
        if (!scenes.containsKey(sceneName)) {
            System.out.println("Scene " + sceneName + " not found.");
            return;
        }

        if (transition != null) {
            transitioning = true;
            transitionTime = 0;
            currentTransition = transition;
            nextSceneName = sceneName;
        } else {
            applySceneChange(sceneName);
        }
    }

    public void update(float deltaTime, SpriteBatch batch) {
        if (transitioning && currentTransition != null) {
            transitionTime += deltaTime;
            if (transitionTime >= currentTransition.getDuration()) {
                applySceneChange(nextSceneName);
                transitioning = false;
            }
        }

        if (game.getScreen() != null) {
            game.getScreen().render(deltaTime);
        }

        if (transitioning) {
            currentTransition.renderTransition(batch, transitionTime);
        }
    }

    private void applySceneChange(String sceneName) {
        game.setScreen(scenes.get(sceneName));
    }

    public void disposeAllScenes() {
        for (Scene scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }

	
}
