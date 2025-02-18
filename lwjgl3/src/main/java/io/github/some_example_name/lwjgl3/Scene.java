package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Scene implements Screen {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private Texture background;
    private Texture button;
    private Texture restartButton;
    private Texture closeButton;
    private SceneType sceneType;
    private int playerHealth = 5;
    
    private Vector2 buttonPosition;
    private Vector2 restartButtonPosition;
    private Vector2 closeButtonPosition;

    public enum SceneType {
        MAIN_MENU, GAMEPLAY, END_SCREEN
    }

    public Scene(SceneManager sceneManager, SceneType sceneType) {
        this.sceneManager = sceneManager;
        this.sceneType = sceneType;
        batch = new SpriteBatch();

        switch (sceneType) {
            case MAIN_MENU:
                background = new Texture("main_menu_bg.png");
                button = new Texture("start_button.png");
                buttonPosition = new Vector2(300, 200);
                break;

            case GAMEPLAY:
                background = new Texture("game_bg.png");
                break;

            case END_SCREEN:
                background = new Texture("end_screen_bg.png");
                restartButton = new Texture("restart_button.png");
                closeButton = new Texture("close_button.png");
                restartButtonPosition = new Vector2(250, 200);
                closeButtonPosition = new Vector2(450, 200);
                break;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        batch.draw(background, 0, 0);

        if (sceneType == SceneType.MAIN_MENU) {
            batch.draw(button, buttonPosition.x, buttonPosition.y);
        } else if (sceneType == SceneType.END_SCREEN) {
            batch.draw(restartButton, restartButtonPosition.x, restartButtonPosition.y);
            batch.draw(closeButton, closeButtonPosition.x, closeButtonPosition.y);
        }

        batch.end();

        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Convert to game coordinates

            System.out.println("Click detected at: " + x + ", " + y);
            
            SceneTransition fadeTransition = new SceneTransition(1.5f);

            if (sceneType == SceneType.MAIN_MENU && isButtonClicked(x, y, buttonPosition, button)) {
                System.out.println("Start button clicked! Switching to GamePlay...");
                sceneManager.switchScene("GamePlay", fadeTransition);
            } else if (sceneType == SceneType.END_SCREEN) {
                if (isButtonClicked(x, y, restartButtonPosition, restartButton)) {
                    System.out.println("Restart button clicked! Switching to GamePlay...");
                    sceneManager.switchScene("GamePlay", fadeTransition);
                } else if (isButtonClicked(x, y, closeButtonPosition, closeButton)) {
                    System.out.println("Close button clicked! Exiting game...");
                    Gdx.app.exit(); // Closes the application
                }
            }
        }
    }

    private boolean isButtonClicked(int x, int y, Vector2 buttonPos, Texture buttonTexture) {
        boolean clicked = x >= buttonPos.x && x <= buttonPos.x + buttonTexture.getWidth()
                       && y >= buttonPos.y && y <= buttonPos.y + buttonTexture.getHeight();
        
        if (clicked) {
            System.out.println("Button Clicked at: " + x + ", " + y);
        }
        return clicked;
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        if (button != null) button.dispose();
        if (restartButton != null) restartButton.dispose();
        if (closeButton != null) closeButton.dispose();
    }
}
