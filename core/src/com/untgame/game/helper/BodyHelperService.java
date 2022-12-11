package com.untgame.game.helper;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pools;

import static com.untgame.game.helper.Constants.PPM;
import static com.untgame.game.helper.ContactType.BULLET;

public class BodyHelperService {

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, World level, ContactType type) {
        BodyDef bodyDef = new BodyDef(); // Body definition.
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set( x / PPM, y / PPM);
        bodyDef.fixedRotation = true;
        Body body = level.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        if (type == BULLET) fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData(type);
        shape.dispose();
        return body;
    }

}
