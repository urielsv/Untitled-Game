package com.untgame.game.objects.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static com.untgame.game.helper.Constants.PPM;

public abstract class GameEntity {

    protected float x, y, velX, velY, speed;
    protected float width, height;
    protected Body body;
    public Sprite sprite;
    Texture texture;

    public GameEntity(float width, float height, Body body, Texture texture) {
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.width = width;
        this.height = height;
        this.body = body;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
        this.texture = texture;

        // sus
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Body getBody() {
        return body;
    }



}
