package com.untgame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.untgame.game.UntitledGame;

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        game.batch.begin();

            game.font.draw(game.batch, "Play", screenCenterX - 200, screenCenterY + 60);
		    game.font.draw(game.batch, "Settings", screenCenterX - 200, screenCenterY + 40);
		    game.font.draw(game.batch, "Quit", screenCenterX - 200, screenCenterY + 20);

            // Play
            if ((Gdx.input.getX() >= screenCenterX - 200) && (Gdx.input.getX() <= screenCenterX - 100)
                    && (Gdx.input.getY() >= screenCenterY - 60) && (Gdx.input.getY() <= screenCenterY - 40) && Gdx.input.isTouched()) {
                game.setScreen(new GameScreen());
                dispose();
            } else {
                //game.font.draw(game.batch, "xd", screenCenterX, screenCenterY);
            }

            // Quit.
            if ((Gdx.input.getX() >= screenCenterX - 200) && (Gdx.input.getX() <= screenCenterX - 100)
                && (Gdx.input.getY() >= screenCenterY - 20) && (Gdx.input.getY() <= screenCenterY) && Gdx.input.isTouched()) {

                Gdx.app.exit();
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
