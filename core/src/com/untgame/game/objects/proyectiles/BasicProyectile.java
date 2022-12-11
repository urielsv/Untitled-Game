package com.untgame.game.objects.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.helper.ContactType;
import com.untgame.game.objects.player.GameEntity;
import com.untgame.game.screens.GameScreen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static com.untgame.game.helper.Constants.*;

public class BasicProyectile implements ContactListener {
    public static final int SPEED = (int) PPM * 12;
    public boolean remove;
    private static Texture texture;
    float x,y;
    private int width, height;
    private float angle;
    private GameScreen gameScreen;
    Body body;

    public BasicProyectile(float x, float y, float angle, GameScreen gameScreen) {
        this.x=x;
        this.y=y;
        this.angle = angle;
        this.gameScreen = gameScreen;
        this.width = (int) PPM/2;
        this.height = (int) PPM/2;


        if (texture == null) texture = new Texture("BasicProyectile.png");

        this.body = BodyHelperService.createBody(x,y, width, height, false, gameScreen.getLevel(), ContactType.BULLET);
        body.setBullet(true);

    }


    public void update (float deltaTime){

        this.y += Math.sin(angle) * (SPEED * deltaTime);
        this.x += Math.cos(angle) * (SPEED * deltaTime);

        this.body.setLinearVelocity((float) (SPEED * Math.cos(angle)), (float) (SPEED * Math.sin(angle)));
        if (y > Gdx.graphics.getHeight() * 2 || x > Gdx.graphics.getWidth() * 2 || x < 0 || y < 0)
            remove = true;
        //System.out.println("Bullet pos:" + "(" + x + "," + y + ")");
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

