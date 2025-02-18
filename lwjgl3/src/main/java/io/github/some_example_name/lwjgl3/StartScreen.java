package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;

public class StartScreen implements Screen {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private Texture background;
    private BitmapFont font;

    public StartScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        batch = new SpriteBatch();
        background = new Texture("start_screen_bg.png"); // Ensure this file is in assets
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(batch, "Press ENTER to Start Game", 
                  Gdx.graphics.getWidth() / 2 - 150, 
                  Gdx.graphics.getHeight() / 2);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            SceneTransition fadeTransition = new SceneTransition(1.5f);
            sceneManager.switchScene("GamePlay", fadeTransition);
        }
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
        font.dispose();
    }
}
