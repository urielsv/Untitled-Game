package com.untgame.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.Proyectile;
import com.untgame.game.screens.GameScreen;

import java.util.ArrayList;

import static com.untgame.game.helper.Constants.*;

public class ProyectileHelper<K extends Proyectile> {

    private ShootCooldownHelper shotCooldown;
    private final Player player;
    private final OrthographicCamera camera;
    private final GameScreen gameScreen;
    private final ArrayList<K> bullets;

    public ProyectileHelper(ShootCooldownHelper shootCooldown, Player player, OrthographicCamera camera, GameScreen gameScreen, ArrayList<K> bullets){
        this.shotCooldown=shootCooldown;
        this.player=player;
        this.camera = camera;
        this.gameScreen = gameScreen;
        this.bullets=bullets;
    }

    public void setCooldown(float newCooldown){
        shotCooldown.setCoolDown(newCooldown);
    }

    public void shoot() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && shotCooldown.getCoolDown() >= SHOT_DELAY) {

            Vector3 playerPos = new Vector3(
                    (player.getBody().getPosition().x - player.getWidth() / 4 / PPM) * PPM,
                    (player.getBody().getPosition().y - player.getHeight() / 4 / PPM) * PPM,
                    0
            );

            Vector3 bulletPos = new Vector3(player.getBody().getPosition().x * PPM, player.getBody().getPosition().y * PPM, 0);

            Vector3 theta = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            //Vector3 theta = new Vector3(Gdx.input.getX() - x , Gdx.input.getY() - y, 0);
            camera.unproject(theta);

            //double norm = Math.sqrt(Math.pow((cursorLocation.x - x), 2) + Math.pow((cursorLocation.y - y), 2));

            float rads = (float) Math.atan2(theta.y - bulletPos.y, theta.x - bulletPos.x);

            System.out.println(playerPos.x);
            System.out.println(playerPos.y);

            shotCooldown.setCoolDown(0);
            bullets.add((K) new Proyectile(new Vector2(playerPos.x, playerPos.y), rads, DEFAULT_PROYECTILE_SPEED, gameScreen));
        }
    }

}
