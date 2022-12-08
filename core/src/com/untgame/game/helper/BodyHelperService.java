package com.untgame.game.helper;

import com.badlogic.gdx.physics.box2d.*;

import static com.untgame.game.helper.Constants.PPM;

public class BodyHelperService {

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World level) {
        BodyDef bodyDef = new BodyDef(); // Body definition.
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( x / PPM, y / PPM);
        bodyDef.fixedRotation = true;
        Body body = level.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;

    }
}
