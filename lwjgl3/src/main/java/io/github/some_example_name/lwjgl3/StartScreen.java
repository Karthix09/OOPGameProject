package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartScreen extends Scene {
    private static final float WORLD_WIDTH = 1344; // Image width
    private static final float WORLD_HEIGHT = 768; // Image height

    private SpriteBatch batch;
    private Texture background;
    private BitmapFont font;
    private Viewport viewport;

    public StartScreen(SceneManager sceneManager) {
        super(sceneManager);
        batch = new SpriteBatch();
        background = new Texture("main_menu_bg.png");
        font = new BitmapFont();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(batch, "Press ENTER to Start Game", WORLD_WIDTH / 2 - 150, WORLD_HEIGHT / 2);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            SceneTransition fadeTransition = new SceneTransition(1.5f);
            sceneManager.switchScene("GamePlay", fadeTransition);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {}
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
