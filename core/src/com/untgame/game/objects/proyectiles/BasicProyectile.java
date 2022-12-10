package com.untgame.game.objects.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Pool;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.objects.player.GameEntity;
import com.untgame.game.screens.GameScreen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static com.untgame.game.helper.Constants.PPM;

public class BasicProyectile {
    public static final int SPEED = 220;
    public boolean remove;
    private static Texture texture;
    float x,y;
    private float angle;
    public Sprite sprite;
    private GameScreen gameScreen;
    FixtureDef fixture;
    BodyDef bodyDef;
    Body body;

    public BasicProyectile(float x, float y, float angle) {
        this.x=x;
        this.y=y;
        this.angle = angle;
        if (texture == null) {
            texture = new Texture("BasicProyectile.png");
        }

        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        sprite.setSize(PPM, PPM);


    }


    public void update (float deltaTime){

        y += Math.sin(angle) * (SPEED * deltaTime);
        x += Math.cos(angle) * (SPEED * deltaTime);

        if (y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth())
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }
}

