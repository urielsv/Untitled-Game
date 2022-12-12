package com.untgame.game.helper;

import com.badlogic.gdx.physics.box2d.*;
import com.untgame.game.objects.player.Player;
import com.untgame.game.objects.proyectiles.Proyectile;

public class ContactListenerHelper implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (fA == null || fB == null) return;
        if (fA.getUserData() == null || fB.getUserData() == null) return;

        System.out.println("Collisioned");
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (fA == null || fB == null) return;
        if (fA.getUserData() == null || fB.getUserData() == null) return;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean checkContact(Fixture a, Fixture b) {
        // check si son objetos de BodyHelperService.
        return (a.getUserData() instanceof Proyectile && b.getUserData() instanceof TileMapHelper);
    }
}
