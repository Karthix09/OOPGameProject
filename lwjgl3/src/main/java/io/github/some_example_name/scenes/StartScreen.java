package io.github.some_example_name.scenes;

import com.badlogic.gdx.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.some_example_name.inputoutput.IOManager;

public class StartScreen extends Scene {
    private static final float WORLD_WIDTH = 1344; // Image width
    private static final float WORLD_HEIGHT = 768; // Image height

    private SpriteBatch batch;
    private Texture background;
    private BitmapFont font;
    private Viewport viewport;
    private IOManager ioManager;

    public StartScreen(SceneManager sceneManager) {
        super(sceneManager);
        batch = new SpriteBatch();
        background = new Texture("main_menu_bg2.jpg");
        font = new BitmapFont();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.apply();
        
        ioManager = new IOManager(sceneManager); // Initialize IOManager
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
        font.draw(batch, "Welcome to the Healthy Eating Game!", WORLD_WIDTH / 2 - 300, WORLD_HEIGHT - 650);
        font.draw(batch, "Click ANYWHERE on the screen to Start Game!", WORLD_WIDTH / 2 - 300, WORLD_HEIGHT - 700);

        batch.end();

        //check if left click on mouse is clicked
        ioManager.getMouseInput().isLeftClicked();  
        
        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

   

    @Override
    public void dispose() {
        batch.dispose();
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
