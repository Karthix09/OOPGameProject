package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Game game;
    private Map<String, Screen> scenes;
    private Screen activeScene;
    private SceneTransition activeTransition;
    private float transitionTime;
    private boolean transitioning;
    private String nextSceneName;
    private SpriteBatch batch;

    public SceneManager(Game game) {
        this.game = game;
        this.scenes = new HashMap<>();
        this.batch = new SpriteBatch();
    }

    public void createScenes() {
        addScene("StartScreen", new Scene(this, "StartScreen"));
        addScene("GamePlay", new Scene(this, "GamePlay"));
        addScene("EndScreen", new Scene(this, "EndScreen"));

        switchScene("StartScreen", null);
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

        if (transition != null) {
            transitioning = true;
            activeTransition = transition;
            transitionTime = 0;
            nextSceneName = sceneName;
        } else {
            applySceneChange(sceneName);
        }
    }

    public void update(float delta) {
        if (transitioning && activeTransition != null) {
            transitionTime += delta;
            if (transitionTime >= activeTransition.getDuration()) {
                applySceneChange(nextSceneName);
                transitioning = false;
            }
        }

        if (activeScene != null) {
            activeScene.render(delta);
        }
    }

    public void render() {
        if (activeScene != null) {
            activeScene.render(0);
        }
        if (transitioning && activeTransition != null) {
            activeTransition.renderTransition(batch, transitionTime);
        }
    }

    private void applySceneChange(String sceneName) {
        activeScene = scenes.get(sceneName);
        game.setScreen(activeScene);
    }

    public void resize(int width, int height) {
        if (activeScene != null) {
            activeScene.resize(width, height);
        }
    }

    public void disposeAllScenes() {
        for (Screen scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
        batch.dispose();
    }
}