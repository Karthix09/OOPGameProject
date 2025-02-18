package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;

public class EndScreen implements Screen {
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private Texture background;
    private BitmapFont font;

    public EndScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        batch = new SpriteBatch();
        background = new Texture("end_screen_bg.png");
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(batch, "Game Over!", Gdx.graphics.getWidth() / 2 - 60, Gdx.graphics.getHeight() / 2 + 50);
        font.draw(batch, "Press ENTER to Restart", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        font.draw(batch, "Press ESC to Exit", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2 - 30);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            sceneManager.switchScene("GamePlay", new SceneTransition(1.5f));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
