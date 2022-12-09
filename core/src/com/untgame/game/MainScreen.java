package com.untgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.untgame.game.helper.Constants.*;
import static com.untgame.game.helper.Constants.PPM;

public class MainScreen implements Screen {

    private UntitledGame game;

    public MainScreen(UntitledGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

            game.font.draw(game.batch, "Play", 0, 0);
		    game.font.draw(game.batch, "Settings", 100, 100-20);
		    game.font.draw(game.batch, "Quit", 100, 100-40);

            if (Gdx.input.getX() >= 100-20 && Gdx.input.isTouched()){
                game.setScreen(new GameScreen());
                dispose();
            } else {
                game.font.draw(game.batch, "LOL", 100, 100-60);
            }
		    game.batch.draw(new Texture("player.png"), 5, 5, 256, 256);

		game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {

    }
}
