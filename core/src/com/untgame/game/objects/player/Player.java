package com.untgame.game.objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


import static com.untgame.game.helper.Constants.PLAYER_SPEED;
import static com.untgame.game.helper.Constants.PPM;
import static java.lang.Math.pow;

public class Player extends GameEntity {

    public Player(float width, float height, Body body, Texture texture) {
        super(width, height, body, texture);
        this.speed = PLAYER_SPEED;
    }

    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }


    @Override
    public void update() {
        x = body.getPosition().x ;
        y = body.getPosition().y ;

        checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, (x - width / 2 / PPM) * PPM , (y - height / 2 / PPM) * PPM);

    }

    private void checkUserInput() {
        velX = 0;
        velY = 0;

        // WASD (Controls)
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            velY = 1;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            velX = -1;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            velY = -1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            velX = 1;
        }

        // Diagonals
       if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            velX = (float) pow(2, -1/2f);
            velY = (float) pow(2, -1/2f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            velX = (float) -pow(2, -1/2f);
            velY = (float) pow(2, -1/2f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            velX = (float) pow(2, -1/2f);
            velY = (float) -pow(2, -1/2f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            velX = (float) -pow(2, -1/2f);
            velY = (float) -pow(2, -1/2f);
        }

        body.setLinearVelocity(velX * speed, velY * speed);

    }

}
