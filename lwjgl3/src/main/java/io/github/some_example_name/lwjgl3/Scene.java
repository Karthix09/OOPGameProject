package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Scene implements Screen {
    private SceneManager sceneManager;
    protected SpriteBatch batch;
    private BitmapFont font;
    private Texture background;
    private String sceneType;
    private GlyphLayout layout;

    private OrthographicCamera camera;
    private Viewport viewport;

    private final int VIRTUAL_WIDTH = 1280;
    private final int VIRTUAL_HEIGHT = 720;

    public Scene(SceneManager sceneManager, String sceneType) {
        this.sceneManager = sceneManager;
        this.sceneType = sceneType;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        this.viewport.apply();
        camera.position.set(VIRTUAL_WIDTH / 2f, VIRTUAL_HEIGHT / 2f, 0);
        camera.update();

        switch (sceneType) {
            case "StartScreen":
                background = new Texture("main_menu_bg.png");
                break;
            case "GamePlay":
                background = new Texture("game_bg.png");
                break;
            case "EndScreen":
                background = new Texture("end_screen_bg.png");
                break;
            default:
                background = null;
        }
    }

    public void update(float delta) {
        switch (sceneType) {
            case "StartScreen":
                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                    sceneManager.switchScene("GamePlay", new SceneTransition(1.5f));
                }
                break;
            case "GamePlay":
                if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                    sceneManager.switchScene("EndScreen", new SceneTransition(1.5f));
                }
                break;
            case "EndScreen":
                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                    sceneManager.switchScene("GamePlay", new SceneTransition(1.5f));
                } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                    Gdx.app.exit();
                }
                break;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        if (background != null) {
            batch.draw(background, 0, 0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        }

        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        switch (sceneType) {
            case "StartScreen":
                layout.setText(font, "Press ENTER to Start Game");
                font.draw(batch, layout, (VIRTUAL_WIDTH - layout.width) / 2, VIRTUAL_HEIGHT / 2);
                break;
            case "EndScreen":
                layout.setText(font, "Game Over!");
                font.draw(batch, layout, (VIRTUAL_WIDTH - layout.width) / 2, VIRTUAL_HEIGHT / 2 + 50);

                layout.setText(font, "Press ENTER to Restart");
                font.draw(batch, layout, (VIRTUAL_WIDTH - layout.width) / 2, VIRTUAL_HEIGHT / 2);

                layout.setText(font, "Press ESC to Exit");
                font.draw(batch, layout, (VIRTUAL_WIDTH - layout.width) / 2, VIRTUAL_HEIGHT / 2 - 30);
                break;
        }

        batch.end();
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (background != null) background.dispose();
    }

    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}