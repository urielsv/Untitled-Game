package com.untgame.game.objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static com.untgame.game.helper.Constants.PPM;

public class Player extends GameEntity {


    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 8f;
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

        body.setLinearVelocity(velX * speed, velY * speed);

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
