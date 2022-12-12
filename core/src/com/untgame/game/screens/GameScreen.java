package com.untgame.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.untgame.game.helper.ContactListenerHelper;
import com.untgame.game.helper.ProyectileHelper;
import com.untgame.game.helper.ShootCooldownHelper;
import com.untgame.game.helper.TileMapHelper;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.Proyectile;
import com.untgame.game.scenes.GameHud;

import java.util.ArrayList;

import static com.untgame.game.helper.Constants.*;

public class GameScreen implements Screen, InputProcessor {

    //private final UntitledGame game;

    OrthographicCamera camera;
    private SpriteBatch batch;
    private World level; // levels
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;



    private Player player;
    private GameHud hud;

    ArrayList<Proyectile> bullets = new ArrayList<>();

    private ShootCooldownHelper shotCooldown= new ShootCooldownHelper(SHOT_DELAY);
    private ProyectileHelper<Proyectile> proyectileHelper;


    public GameScreen() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(GAME_WIDTH*16/9, GAME_HEIGHT);

        hud = new GameHud(this.batch);
        level = new World(new Vector2(0, 0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        tileMapHelper = new TileMapHelper(this);
        orthogonalTiledMapRenderer = tileMapHelper.setupMap();
        this.level.setContactListener(new ContactListenerHelper());
        proyectileHelper = new ProyectileHelper<>(shotCooldown, player, camera, this, bullets);
        //camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();


        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0,0, 0);
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

        shotCooldown.setCoolDown(shotCooldown.getCoolDown()+0.1f);
        proyectileHelper.shoot();
        // BULLETS!!!!1




        ArrayList<Proyectile> remBullets = new ArrayList<>();
        for (Proyectile bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove)
                remBullets.add(bullet);
        }
        bullets.removeAll(remBullets);


        batch.begin();

        player.draw(batch);

        for (Proyectile bullet : bullets) {
            bullet.render(this.batch);
        }
        player.render(this.batch);

        batch.end();

        hud.stage.getViewport().apply();
        hud.stage.act();
        hud.stage.draw();

        // Hold to show hitboxes.
        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            box2DDebugRenderer.render(level, camera.combined.scl(PPM));
        }
        // direc[dir].img

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public World getLevel() {
        return level;
    }

    private void update() {
        level.step(1f / REFRESH_RATE, 6, 2);
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
        //camera.position.set(position);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
