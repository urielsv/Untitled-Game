package com.untgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final UntitledGame game;

    OrthographicCamera camera;
    Rectangle player;
    Texture playerImg;
    int imgSize;

    public GameScreen(final UntitledGame game) {
        this.game = game;

        playerImg = new Texture("player1.png");

        imgSize = 256; // TEMP

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Defs.SCREEN_WIDTH.getValue(), Defs.SCREEN_HEIGHT.getValue());

        player = new Rectangle();
        // Centered.
        player.x = Defs.SCREEN_WIDTH.getValue() / 2f - imgSize / 2f;
        player.y = Defs.SCREEN_HEIGHT.getValue() / 2f - imgSize / 2f;

        player.width = imgSize;
        player.height = imgSize;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.2f, 0.2f, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Ingame test.", 5, 100);
        game.batch.draw(playerImg, player.x, player.y, player.width, player.height);
        game.batch.end();


        // WASD (Controls)
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            player.y += 400 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            player.x -= 400 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            player.y -= 400 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            player.x += 400 * Gdx.graphics.getDeltaTime();

        // BORDERS

        if (player.x < 0)
            player.x = 0;
        if (player.x > Defs.SCREEN_WIDTH.getValue() - imgSize)
            player.x = Defs.SCREEN_WIDTH.getValue() - imgSize;
        if (player.y < 0)
            player.y = 0;
        if (player.y > Defs.SCREEN_HEIGHT.getValue() - imgSize)
            player.y = Defs.SCREEN_HEIGHT.getValue() - imgSize;

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
        playerImg.dispose();
    }
}
