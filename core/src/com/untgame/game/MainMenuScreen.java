package com.untgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final UntitledGame game;

    OrthographicCamera camera;

    public MainMenuScreen(final UntitledGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Defs.SCREEN_WIDTH.getValue(), Defs.SCREEN_HEIGHT.getValue());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Play", 100, 500);
		game.font.draw(game.batch, "Settings", 100, 500-20);
		game.font.draw(game.batch, "Quit", 100, 500-40);

		Texture menuImg = new Texture("player.png");
		game.batch.draw(menuImg, 600, 300, 256, 256);
		game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
