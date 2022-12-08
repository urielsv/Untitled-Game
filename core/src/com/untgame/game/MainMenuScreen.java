package com.untgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen extends ScreenAdapter {

    final UntitledGame game;

    int widthScreen, heightScreen;
    OrthographicCamera camera;

    public MainMenuScreen(final UntitledGame game) {
        this.game = game;

        camera = new OrthographicCamera();

        this.widthScreen = Gdx.graphics.getWidth();
        this.heightScreen = Gdx.graphics.getHeight();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, widthScreen/2, heightScreen/2);

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
        game.font.draw(game.batch, "Play", 100, 100);
		game.font.draw(game.batch, "Settings", 100, 100-20);
		game.font.draw(game.batch, "Quit", 100, 100-40);

        if (Gdx.input.getX() >= 100-20 && Gdx.input.isTouched()){
            game.setScreen(new GameScreen(camera));
            dispose();
        } else {
            game.font.draw(game.batch, "LOL", 100, 100-60);
        }

        Texture menuImg = new Texture("player.png");
		game.batch.draw(menuImg, 200, 100, 256, 256);
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
