package com.untgame.game.objects.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class BasicProyectile {
    public static final int SPEED = 220;
    public boolean remove;
    private static Texture texture;
    float x,y;

    public BasicProyectile(float x, float y) {
        this.x=x;
        this.y=y;
        if (texture == null) {
            texture = new Texture("bullet.png");
        }
    }

    public void update (float deltaTime){

        Vector2 cursorLocation = new Vector2(0, 0);
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = Gdx.input.getY();

        // (x,y) cursor
        // (a,b) posicion jugador

        // y - b / x - a = tan tita
        double norm = Math.sqrt(Math.pow((cursorLocation.x - x), 2) + Math.pow((cursorLocation.y - y), 2));
        double thetaX = (cursorLocation.x - x) / norm;
        double thetaY = (cursorLocation.y - y) / norm;
        // V sin = vert
        // V cos =  hori

        // Yo quiero que se mantenga constante mi theta para cada bullet.
        y += thetaY * (SPEED * deltaTime);
        x += thetaX * (SPEED * deltaTime);

        System.out.println(thetaX);
        System.out.println(thetaY);
        //x += SPEED * deltaTime;
        if (y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth())
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);

    }
}

