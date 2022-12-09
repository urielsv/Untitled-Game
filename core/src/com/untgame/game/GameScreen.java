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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.helper.TileMapHelper;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.BasicProyectile;

import java.util.ArrayList;

import static com.untgame.game.helper.Constants.*;

public class GameScreen extends ScreenAdapter {

    //private final UntitledGame game;

    OrthographicCamera camera;
    private SpriteBatch batch;
    private World level; // levels
    private Box2DDebugRenderer box2DDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    private double timer=0;

    Texture playerImg;
    int imgSize;

    private Player player;

    ArrayList<BasicProyectile> bullets;

    public World getLevel() {
        return level;
    }

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.level = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        bullets = new ArrayList<BasicProyectile>();

        imgSize = (int) PPM; // TEMP

        camera = new OrthographicCamera(500, 200);
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

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

        timer += 0.1f;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timer >= 1.5f){

            Vector2 cursorLocation = new Vector2(0, 0);
            cursorLocation.x = Gdx.input.getX();
            cursorLocation.y = Gdx.input.getY();



            float x = (player.getBody().getPosition().x - player.getWidth() / 4 / PPM ) * PPM;
            float y = (player.getBody().getPosition().y - player.getHeight() /4 / PPM) * PPM;



            double norm = Math.sqrt(Math.pow((cursorLocation.x - x), 2) + Math.pow((cursorLocation.y - y), 2));
            double thetaX = (cursorLocation.x - x);
            double thetaY = (cursorLocation.y - y);

            float angle = (float) Math.atan2(thetaX,thetaY);


            bullets.add(new BasicProyectile(x,y , angle));
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
        //font.draw(game.batch, "Ingame test.", 5, 100);

        //game.batch.draw(playerImg, player.x, player.y, player.width, player.height);




    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void update() {
        level.step(1f / REFRESH_RATE, 10, 5);
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
        playerImg.dispose();
    }
}
