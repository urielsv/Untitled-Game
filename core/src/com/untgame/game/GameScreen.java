package com.untgame.game;

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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.helper.TileMapHelper;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.BasicProyectile;

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

    private float screenWidth, screenHeight;
    private Viewport viewport;
    private Player player;
    ArrayList<BasicProyectile> bullets;



    public GameScreen() {
        this.batch = new SpriteBatch();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        level = new World(new Vector2(0, 0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        tileMapHelper = new TileMapHelper(this);
        orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        camera = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT * ( screenHeight / screenWidth));

        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();
        //camera.setToOrtho(false, screenWidth, screenHeight);

        bullets = new ArrayList<BasicProyectile>();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.2f, 0.2f, 1);
        this.update();

            // TESTEO
            if (Gdx.input.isKeyPressed(Input.Keys.Q))
                camera.zoom += 0.02;
            if (Gdx.input.isKeyPressed(Input.Keys.E))
                camera.zoom -= 0.02;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        orthogonalTiledMapRenderer.render();

        // BULLETS!
        timer += 0.1f;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timer >= 1.5f){

            float x = (player.getBody().getPosition().x - player.getWidth() / 4 / PPM ) * PPM;
            float y = (player.getBody().getPosition().y - player.getHeight() / 4 / PPM) * PPM;

            Vector3 theta = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            //Vector3 theta = new Vector3(Gdx.input.getX() - x , Gdx.input.getY() - y, 0);
            camera.unproject(theta);

            //double norm = Math.sqrt(Math.pow((cursorLocation.x - x), 2) + Math.pow((cursorLocation.y - y), 2));

            float rads = (float) Math.atan2(theta.y-y, theta.x-x);

            bullets.add(new BasicProyectile(x, y, rads));
            timer=0;
        }


        ArrayList<BasicProyectile> remBullets = new ArrayList<BasicProyectile>();
        for (BasicProyectile bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove)
                remBullets.add(bullet);
        }
        bullets.removeAll(remBullets);


       // direc[dir].img


        // render objects
        batch.begin();

            player.draw(batch);

            for (BasicProyectile bullet : bullets) {
                bullet.render(this.batch);
            }
            player.render(this.batch);

        batch.end();
        box2DDebugRenderer.render(level, camera.combined.scl(PPM));

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

        orthogonalTiledMapRenderer.setView(camera);

        player.update();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void cameraUpdate() {

        batch.setProjectionMatrix(camera.combined);

        Vector3 position = camera.position;
        // El round es para que la camara sea mas "smooth"
        // No se por que con 15 funciona bien.
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
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
