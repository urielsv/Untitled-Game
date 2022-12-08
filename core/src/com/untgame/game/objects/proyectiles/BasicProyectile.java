package com.untgame.game.objects.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class BasicProyectile{
    public static final int SPEED = 300;
    public boolean remove;
    private static Texture texture;
    float x,y;

    public BasicProyectile(float x, float y) {
        this.x=x;
        this.y=y;
        if (texture == null){
            texture = new Texture("badlogic.jpg");
        }
    }

    public void update (float deltaTime){
        y +=SPEED * deltaTime;
        if (y>Gdx.graphics.getHeight())
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);

    }
}

