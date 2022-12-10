package com.untgame.game.screens;

import com.badlogic.ashley.core.Entity;
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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.helper.TileMapHelper;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.BasicProyectile;
import com.untgame.game.scenes.PlayerHud;

import java.util.ArrayList;

import static com.untgame.game.helper.Constants.*;

public class GameScreen implements Screen {

    //private final UntitledGame game;

    OrthographicCamera camera;
    private SpriteBatch batch;
    private World level; // levels
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    private double timer = 0;

    private Player player;
    private PlayerHud hud;
    ArrayList<BasicProyectile> bullets;



    public GameScreen() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT);

        hud = new PlayerHud(this.batch);
        level = new World(new Vector2(0, 0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        tileMapHelper = new TileMapHelper(this);
        orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        //camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        bullets = new ArrayList<BasicProyectile>();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.update();

        batch.setProjectionMatrix(camera.combined);

        orthogonalTiledMapRenderer.setView(camera);
        orthogonalTiledMapRenderer.render();



        // render objects

        // TESTEO
            if (Gdx.input.isKeyPressed(Input.Keys.Q))
                camera.zoom += 0.02;
            if (Gdx.input.isKeyPressed(Input.Keys.E))
                camera.zoom -= 0.02;


        //batch.setProjectionMatrix(camera.combined);


        // BULLETS!
        timer += 0.1f;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timer >= 1.5f) {

            Vector3 playerPos = new Vector3(
                    (player.getBody().getPosition().x - player.getWidth() / 4 / PPM ) * PPM,
                    (player.getBody().getPosition().y - player.getHeight() / 4 / PPM) * PPM,
                    0
            );

            Vector3 bulletPos = new Vector3(player.getBody().getPosition().x * PPM, player.getBody().getPosition().y * PPM, 0);
            Vector3 theta = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            //Vector3 theta = new Vector3(Gdx.input.getX() - x , Gdx.input.getY() - y, 0);
            camera.unproject(theta);

            //double norm = Math.sqrt(Math.pow((cursorLocation.x - x), 2) + Math.pow((cursorLocation.y - y), 2));

            float rads = (float) Math.atan2(theta.y-bulletPos.y, theta.x-bulletPos.x);

            bullets.add(new BasicProyectile(playerPos.x, playerPos.y, rads));
            timer = 0;
        }

        ArrayList<BasicProyectile> remBullets = new ArrayList<BasicProyectile>();
        for (BasicProyectile bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove)
                remBullets.add(bullet);
        }
        bullets.removeAll(remBullets);


        batch.begin();

        player.draw(batch);

        for (BasicProyectile bullet : bullets) {
            bullet.render(this.batch);
        }
        player.render(this.batch);

        batch.end();

        hud.stage.getViewport().apply();
        hud.stage.act();
        hud.stage.draw();

        box2DDebugRenderer.render(level, camera.combined.scl(PPM));
        // direc[dir].img

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public World getLevel() {
        return level;
    }

    private void update() {
        level.step(1f / REFRESH_RATE, 6, 5);
        cameraUpdate();

        player.update();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void cameraUpdate() {

        //batch.setProjectionMatrix(camera.combined);

        Vector3 position = camera.position;
        // El round es para que la camara sea mas "smooth"
        // No se por que con 15 funciona bien.
        position.x = Math.round(player.getBody().getPosition().x * PPM * 16f) / 16f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 16f) / 16f;
        camera.position.set(position);
        camera.update();
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
