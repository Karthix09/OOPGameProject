package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene implements Screen{
	
	 private SceneManager sceneManager;
	    private SpriteBatch batch;
	    private Texture background;
	    private Texture button;
	    private Texture player;
	    private SceneType sceneType;
	    private int playerHealth = 5; 

	    public enum SceneType {
	        MAIN_MENU, GAMEPLAY, GAME_OVER
	    }

	    public Scene(SceneManager sceneManager, SceneType sceneType) {
	        this.sceneManager = sceneManager;
	        this.sceneType = sceneType;
	        batch = new SpriteBatch();

	        // Load assets based on scene type
	        switch (sceneType) {
	            case MAIN_MENU:
	                background = new Texture("main_menu_bg.png");
	                button = new Texture("start_button.png");
	                break;
	            case GAMEPLAY:
	                background = new Texture("game_bg.png");
	                player = new Texture("player.png");
	                break;
	            case GAME_OVER:
	                background = new Texture("game_over_bg.png");
	                button = new Texture("retry_button.png");
	                break;
	        }
	    }

	    @Override
	    public void render(float delta) {
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        batch.begin();
	        
	        // Render scene based on type
	        batch.draw(background, 0, 0);

	        if (sceneType == SceneType.MAIN_MENU || sceneType == SceneType.GAME_OVER) {
	            batch.draw(button, 300, 200);
	        } else if (sceneType == SceneType.GAMEPLAY) {
	            batch.draw(player, 200, 150);
	        }

	        batch.end();

	        // Scene logic
	        if (Gdx.input.justTouched()) {
	            SceneTransition fadeTransition = new SceneTransition(1.5f);
	            
	            if (sceneType == SceneType.MAIN_MENU) {
	                sceneManager.switchScene("GamePlay", fadeTransition);
	            } else if (sceneType == SceneType.GAMEPLAY) {
	                playerHealth--;
	                if (playerHealth <= 0) {
	                    sceneManager.switchScene("GameOver", fadeTransition);
	                }
	            } else if (sceneType == SceneType.GAME_OVER) {
	                sceneManager.switchScene("GamePlay", fadeTransition);
	            }
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
	        if (button != null) button.dispose();
	        if (player != null) player.dispose();
	    }

	

}
