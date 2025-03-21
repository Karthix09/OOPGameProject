package io.github.some_example_name.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.github.some_example_name.inputoutput.IOManager;
import io.github.some_example_name.score.ScoreManager;

public class EndScreen extends Scene {
    private static final float WORLD_WIDTH = 1344;
    private static final float WORLD_HEIGHT = 768;

    private Texture background;
    private BitmapFont font;
    private IOManager iomanager;
    private boolean isWin;
    private ScoreManager scoreManager;
    
    public EndScreen(SceneManager sceneManager) {
        this(sceneManager, false, null); // default to lose screen
    }
    
    public EndScreen(SceneManager sceneManager, boolean isWin, ScoreManager scoreManager) {
        super(sceneManager);
        background = new Texture("healthyplate.jpg");
        font = new BitmapFont();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewport.apply();
        this.isWin = isWin;
        this.iomanager = new IOManager(sceneManager);
        this.scoreManager = scoreManager;
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
        
        // Align text to the left
        float textX = 50; // Adjust this for exact alignment
        float textY = WORLD_HEIGHT - 100;
        
        font.draw(batch, "Thank you for playing!", textX, textY);
        font.draw(batch, isWin ? "You Win!" : "Game Over!", textX, textY - 50);
        font.draw(batch, "Press ENTER to Restart", textX, textY - 100);
        font.draw(batch, "Press ESC to Exit", textX, textY - 150);
        
        if (scoreManager != null) {
            font.draw(batch, "You collected:", textX, textY - 250);
            font.draw(batch, "- " + scoreManager.getProteinCount() + " Protein", textX + 30, textY - 300);
            font.draw(batch, "- " + scoreManager.getVegetableCount() + " Vegetable", textX + 30, textY - 340);
            font.draw(batch, "- " + scoreManager.getCarbohydrateCount() + " Carbohydrate", textX + 30, textY - 380);
            font.draw(batch, "- " + scoreManager.getIceCreamCount() + " Ice Cream", textX + 30, textY - 420);
        }
        
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
