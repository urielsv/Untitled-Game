package com.untgame.game.objects.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BasicProyectile {
    public static final int SPEED = 220;
    public boolean remove;
    private static Texture texture;
    float x,y;
    private float angle;

    public BasicProyectile(float x, float y, float angle) {
        this.x=x;
        this.y=y;
        this.angle = angle;
        if (texture == null) {
            texture = new Texture("bullet.png");
        }
    }


    public void update (float deltaTime){

        // (x,y) cursor
        // (a,b) posicion jugador

        // y - b / x - a = tan tita

        // V sin = vert
        // V cos =  hori

        // Yo quiero que se mantenga constante mi theta para cada bullet.

        y += Math.sin(angle) * (SPEED * deltaTime);
        x += Math.cos(angle) * (SPEED * deltaTime);

        System.out.println("a");
        System.out.println();
        //x += SPEED * deltaTime;
        if (y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth())
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);

    }
}

