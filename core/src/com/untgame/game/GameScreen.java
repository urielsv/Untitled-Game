package com.untgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.helper.TileMapHelper;
import com.untgame.game.objects.player.Player;

import static com.untgame.game.helper.Constants.*;

public class GameScreen extends ScreenAdapter {

    //private final UntitledGame game;

    OrthographicCamera camera;
    private SpriteBatch batch;
    private World level; // levels
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    Rectangle rect;

    Texture playerImg;
    int imgSize;

    private Player player;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.level = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper();
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        playerImg = new Texture("player1.png");

        imgSize = 256; // TEMP

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);


        rect = new Rectangle();

        // Centered.
        rect.x = SCREEN_WIDTH / 2f - imgSize / 2f;
        rect.y = SCREEN_HEIGHT / 2f - imgSize / 2f;
        rect.width = imgSize;
        rect.height = imgSize;

        Body body = BodyHelperService.createBody(rect.x, rect.y, imgSize, imgSize, false, level);
        this.setPlayer(new Player(rect.getWidth(), rect.getHeight(), body));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //ScreenUtils.clear(0, 0.2f, 0.2f, 1);
        this.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();

        batch.begin();
        // render objects

        batch.end();
        box2DDebugRenderer.render(level, camera.combined.scl(PPM));
        //game.font.draw(game.batch, "Ingame test.", 5, 100);
        //game.batch.draw(playerImg, player.x, player.y, player.width, player.height);



        // BORDERS

        if (rect.x < 0)
            rect.x = 0;
        if (rect.x > SCREEN_WIDTH - imgSize)
            rect.x = SCREEN_WIDTH - imgSize;
        if (rect.y < 0)
            rect.y = 0;
        if (rect.y > SCREEN_HEIGHT - imgSize)
            rect.y = SCREEN_HEIGHT - imgSize;

    }

    private void update() {
        level.step(1f / REFRESH_RATE, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);

        player.update();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        // El round es para que la camara sea mas "smooth"
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(position);
        camera.update();
    }

    public void setPlayer(Player player) {
        this.player = player;
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
