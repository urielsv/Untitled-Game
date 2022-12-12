package com.untgame.game.objects.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.untgame.game.helper.BodyHelperService;
import com.untgame.game.helper.ContactType;
import com.untgame.game.screens.GameScreen;

import static com.untgame.game.helper.Constants.PPM;


public class Proyectile implements ContactListener {
    private Body body;
    private Vector2 velocity;
    private Vector2 originPoint;
    private GameScreen gameScreen;
    private Texture texture = new Texture("BasicProyectile.png");
    private int width = (int) PPM/2;
    private int height = (int) PPM/2;
    public boolean remove;



    public Proyectile(Vector2 originPoint,float angle, float speed, GameScreen gameScreen){
        this.originPoint = originPoint;
        this.velocity = new Vector2(speed*(float)Math.cos(angle)/PPM, speed*(float)Math.sin(angle)/PPM);
        this.gameScreen = gameScreen;
        this.body = BodyHelperService.createBody(originPoint.x, originPoint.y, width, height,false, gameScreen.getLevel(), ContactType.BULLET);
        body.setBullet(true);
    }

    public Proyectile(Vector2 originPoint,float speed, float angle, GameScreen gameScreen, Texture texture){
        this(originPoint,angle,speed, gameScreen);
        this.texture = texture;
    }

    public Proyectile(Vector2 originPoint, float angle,float speed, GameScreen gameScreen, Texture texture, int width, int height){
        this(originPoint, angle,speed, gameScreen, texture);
        this.width = width;
        this.height = height;
    }

    public void update(float deltaTime){
        originPoint.y += (velocity.y *deltaTime*PPM);
        originPoint.x += (velocity.x *deltaTime*PPM);

        this.body.setLinearVelocity(velocity.x, velocity.y);
        if (originPoint.y > Gdx.graphics.getHeight() * 2 || originPoint.x > Gdx.graphics.getWidth() * 2 || originPoint.x < 0 || originPoint.y < 0)
            remove = true;
    }


    public void render(SpriteBatch batch){
        batch.draw(texture, originPoint.x, originPoint.y);
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
