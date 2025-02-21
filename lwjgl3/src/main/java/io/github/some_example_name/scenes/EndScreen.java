package io.github.some_example_name.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.github.some_example_name.inputoutput.IOManager;



public class EndScreen extends Scene {
    private static final float WORLD_WIDTH = 1344;
    private static final float WORLD_HEIGHT = 768;

    private Texture background;
    private BitmapFont font;
    private IOManager iomanager;

    public EndScreen(SceneManager sceneManager) {
        super(sceneManager); // Call constructor of the parent class
        background = new Texture("end_screen_bg.png");
        font = new BitmapFont();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.apply();
        
     // Fix: Initialize IOManager
        this.iomanager = new IOManager(sceneManager);
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
        font.draw(batch, "Game Over!", WORLD_WIDTH / 2 - 60, WORLD_HEIGHT / 2 + 50);
        font.draw(batch, "Press ENTER to Restart", WORLD_WIDTH / 2 - 100, WORLD_HEIGHT / 2);
        font.draw(batch, "Press ESC to Exit", WORLD_WIDTH / 2 - 80, WORLD_HEIGHT / 2 - 30);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            sceneManager.restartGame(); // Call the restart method instead of switching scenes
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            iomanager.getKeyboardInput().handleExitInput();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        font.dispose();
    }

	@Override
	public void show() {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
				
	}

	@Override
	public void hide() {
		
		
	}
}
